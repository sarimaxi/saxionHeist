package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class YourGameScreen extends ScalableGameScreen {
    Player player = null;
    ScoreManager scoreManager = null;
    ObstacleManager obstacleManager = null;
    ArrayList<Obstacle> obstacles = new ArrayList<>();


    private Sprite background;
    private float bgX1, bgX2;
    private float bgSpeed = 100;


    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        player = new Player("String",300,400);
        scoreManager = new ScoreManager();
        obstacleManager = new ObstacleManager();

        background = new Sprite("backgroundImage");
        background.setSize(1280, 720);

        bgX1 = 0;
        bgX2 = 1280;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (GameApp.isButtonJustPressed(Input.Buttons.LEFT)) {
            obstacles.add(new Obstacle(1280, 300));
        }
        // Clear
        GameApp.clearScreen();


        // ---- BACKGROUND SCROLLING ----
        bgX1 -= bgSpeed * delta;
        bgX2 -= bgSpeed * delta;

        if (bgX1 <= -1280) {
            bgX1 = bgX2 + 1280;
        }
        if (bgX2 <= -1280) {
            bgX2 = bgX1 + 1280;
        }

        GameApp.drawTexture("background", bgX1, 0, 1280, 720);
        GameApp.drawTexture("background", bgX2, 0, 1280, 720);

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
