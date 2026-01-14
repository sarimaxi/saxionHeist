package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.game.saxionheist.data.TemporaryData;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class CharacterSelectScreen extends ScalableGameScreen {
    private final Button buttonPuja, buttonNancy, buttonAlex;
    private static final int width = 1280, height = 720;

    public CharacterSelectScreen() {
        super(width, height);

        buttonPuja = new Button((float) width / 4, (float) height / 2,200, 200, "Puja");
        buttonNancy= new Button((float) width / 4 * 2, (float) height / 2,200, 200, "Nancy");
        buttonAlex = new Button((float) width / 4 * 3, (float) height / 2,200, 200, "Alex");
    }

    @Override
    public void show() {
        if (!GameApp.hasFont("basic"))
            GameApp.addFont("basic", "fonts/basic.ttf", 100);

        Button.init();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (buttonPuja.isPressed(width, height)) {
            System.out.println("Selected Puja");
            TemporaryData.setCharacter(new Player());
            GameApp.switchScreen("YourGameScreen");
        }
        if (buttonNancy.isPressed(width, height)) {
            System.out.println("Selected Nancy");
            TemporaryData.setCharacter(new Player());
            GameApp.switchScreen("YourGameScreen");
        }
        if (buttonAlex.isPressed(width, height)) {
            System.out.println("Selected Alex");
            TemporaryData.setCharacter(new AlexCharacter()); // Change to Alex character
            GameApp.switchScreen("YourGameScreen");

        }

        // When the user presses enter, go to the next screen
        if (GameApp.isKeyJustPressed(Input.Keys.ENTER))
            GameApp.switchScreen("YourGameScreen");

        // Render the menu
        GameApp.clearScreen("black");

        // Draw text
        GameApp.startSpriteRendering();
        GameApp.drawTextCentered("basic", "Select Character", getWorldWidth() / 2, getWorldHeight() / 2 + 200, "amber-500");
        GameApp.endSpriteRendering();

        // Draw buttons
        buttonPuja.render(delta);
        buttonNancy.render(delta);
        buttonAlex.render(delta);
    }

    @Override
    public void hide() {
        if (GameApp.hasFont("basic"))
            GameApp.disposeFont("basic");

        Button.dispose();
    }
}
