package nl.saxion.game.saxionheist;

import nl.saxion.game.saxionheist.data.TemporaryData;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;

public class CharacterSelectScreen extends ScalableGameScreen {
    private float time = 0f;
    private static final int width = 1280, height = 720;

    private static final String BG = "menu_bg";
    private static final String MONEY = "money_particle";

    private final Button buttonPuja, buttonNancy, buttonAlex;

    private static class MoneyBill {
        float x, y;
        float speed;
        float size;
        float sway;
        float swaySpeed;
    }

    private final ArrayList<MoneyBill> bills = new ArrayList<>();

    public CharacterSelectScreen() {
        super(width, height);

        buttonPuja = new Button((float) width / 4, (float) height / 2, 260, 130, "Puja");
        buttonNancy = new Button((float) width / 2, (float) height / 2, 260, 130, "Nancy");
        buttonAlex = new Button((float) width / 4 * 3, (float) height / 2, 260, 130, "Alex");
    }

    @Override
    public void show() {
        Button.init();

        if (!GameApp.hasTexture(BG))
            GameApp.addTexture(BG, "background.png");

        if (!GameApp.hasTexture(MONEY))
            GameApp.addTexture(MONEY, "money.png");

        bills.clear();
        for (int i = 0; i < 12; i++) {
            MoneyBill b = new MoneyBill();
            b.x = (float) (Math.random() * width);
            b.y = (float) (Math.random() * height);
            b.speed = 12f + (float) (Math.random() * 16f);
            b.size = 80f + (float) (Math.random() * 40f);
            b.sway = 10f + (float) (Math.random() * 16f);
            b.swaySpeed = 0.8f + (float) (Math.random() * 1.2f);
            bills.add(b);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen("black");

        GameApp.startSpriteRendering();
        GameApp.drawTexture(BG, 0, 0, width, height);
        GameApp.endSpriteRendering();

        renderMoney(delta);

        GameApp.startSpriteRendering();
        GameApp.drawTextCentered("TitleFont", "Saxion Heist", width / 2f, height - 120, "white");
        GameApp.drawTextCentered("SubFont", "Choose your character", width / 2f, height - 200, "green-200");
        GameApp.endSpriteRendering();

        buttonPuja.render(delta, width, height);
        buttonNancy.render(delta, width, height);
        buttonAlex.render(delta, width, height);

        GameApp.startSpriteRendering();
        GameApp.drawTextCentered("SmallFont", "Faster run", buttonPuja.x, buttonPuja.y - 120, "green-100");
        GameApp.drawTextCentered("SmallFont", "Double Jump Ability", buttonNancy.x, buttonNancy.y - 120, "green-100");
        GameApp.drawTextCentered("SmallFont", "2 extra HP", buttonAlex.x, buttonAlex.y - 120, "green-100");
        GameApp.endSpriteRendering();

        if (buttonPuja.isPressed(width, height)) {
            System.out.println("Selected Puja");
            TemporaryData.setCharacter(new PujaCharacter());
            GameApp.switchScreen("YourGameScreen");
        }

        if (buttonNancy.isPressed(width, height)) {
            TemporaryData.setCharacter(new NancyCharacter());
            GameApp.switchScreen("YourGameScreen");
        }

        if (buttonAlex.isPressed(width, height)) {
            TemporaryData.setCharacter(new AlexCharacter());
            GameApp.switchScreen("YourGameScreen");
        }
    }

    private void renderMoney(float delta) {
        GameApp.startSpriteRendering();

        time += delta;

        for (MoneyBill b : bills) {
            b.y -= b.speed * delta;

            float drift = (float) Math.sin(time * b.swaySpeed + b.x * 0.01f) * b.sway;

            if (b.y < -120) {
                b.y = height + 120;
                b.x = (float) (Math.random() * width);
            }

            GameApp.drawTexture(MONEY, b.x + drift, b.y, b.size, b.size);
        }

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        Button.dispose();
    }
}


