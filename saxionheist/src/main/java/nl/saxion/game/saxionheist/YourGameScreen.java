package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class YourGameScreen extends ScalableGameScreen {
    ScoreManager scoreManager = null;

    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        scoreManager = new ScoreManager();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear
        GameApp.clearScreen();

        scoreManager.render(delta);
    }

    @Override
    public void hide() {
        scoreManager.dispose();
    }
}
