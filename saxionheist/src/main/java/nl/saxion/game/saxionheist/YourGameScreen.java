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
    Health health;
    GameOverScreen gameOverScreen;
    float hitCooldown = 0;

    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        player = new Player("String", 300, 400);
        scoreManager = new ScoreManager();
        obstacleManager = new ObstacleManager(player);
        health = new Health(3);
        gameOverScreen = new GameOverScreen(scoreManager, health);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        hitCooldown -= delta;
        GameApp.clearScreen();

        if (!gameOverScreen.isActive()) {
            player.render(delta);
            obstacleManager.render(delta);
            scoreManager.render(delta);
            health.render();

            for (Obstacle obstacle : obstacleManager.getObstacles()) {
                if (hitCooldown <= 0 && GameApp.rectOverlap(
                        obstacle.getX(), 300, 32, 32,
                        player.x, player.y, 32, 32
                )) {
                    health.damage(1);
                    hitCooldown = 1.0f;
                    break;
                }
            }

            if (health.isDead()) {
                gameOverScreen.show();
            }
        }

        gameOverScreen.render();
    }

    @Override
    public void hide() {
        scoreManager.dispose();
    }
}
