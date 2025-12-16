package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;

public class YourGameScreen extends ScalableGameScreen {
    Player player = null;
    ScoreManager scoreManager = null;
    ObstacleManager obstacleManager = null;
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        player = new Player("String",300,400);
        scoreManager = new ScoreManager();
        obstacleManager = new ObstacleManager(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (GameApp.isButtonJustPressed(Input.Buttons.LEFT)) {
            obstacles.add(new Obstacle(1280, 300));
        }

        // Clear
        GameApp.clearScreen();

        player.render(delta);
        scoreManager.render(delta);
        obstacleManager.render(delta);

        for (Obstacle obstacle : obstacles) {
            obstacle.render(delta);
        }
    }

    @Override
    public void hide() {
        scoreManager.dispose();
    }
}
