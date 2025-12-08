package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;

public class YourGameScreen extends ScalableGameScreen {
    Player player = null;
    ScoreManager scoreManager = null;
    ObstacleManager obstacleManager = null;

    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        player = new Player("String",300,400);
        scoreManager = new ScoreManager();
        obstacleManager = new ObstacleManager();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

       // Clear
        GameApp.clearScreen();

        scoreManager.render(delta);
        player.render(delta);
        obstacleManager.render(delta);

    }

    @Override
    public void hide() {
        scoreManager.dispose();
    }
}
