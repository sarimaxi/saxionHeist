package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class MainMenuScreen extends ScalableGameScreen {
    public MainMenuScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        GameApp.addFont("basic", "fonts/basic.ttf", 100);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // When the user presses enter, go to the next screen
        if (GameApp.isKeyJustPressed(Input.Keys.ENTER))
            GameApp.switchScreen("CharacterSelectScreen");

        // Render the main menu
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();
        GameApp.drawTextCentered("basic", "SaxionHeist", getWorldWidth() / 2, getWorldHeight() / 2 + 50, "amber-500");
        GameApp.drawTextCentered("basic", "Start Game (press enter)", getWorldWidth() / 2, getWorldHeight() / 2 - 50, "amber-500");
        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeFont("basic");
    }
}
