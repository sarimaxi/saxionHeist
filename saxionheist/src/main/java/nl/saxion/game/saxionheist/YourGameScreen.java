package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class YourGameScreen extends ScalableGameScreen {

    Player player = null;

    ScoreManager scoreManager = null;
    HealthManager healthManager = null;
    ObstacleManager obstacleManager = null;

    GameOverScreen gameOverScreen = null;

    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        player = new Player("String", 300, 400);

        scoreManager = new ScoreManager();
        healthManager = new HealthManager(3);
        obstacleManager = new ObstacleManager(player, healthManager);

        gameOverScreen = new GameOverScreen(scoreManager, healthManager);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear
        GameApp.clearScreen();

        if (!gameOverScreen.isActive()) {
            player.render(delta);
            obstacleManager.render(delta);
            scoreManager.render(delta);
            healthManager.render(delta);

            if (healthManager.isDead())
                gameOverScreen.show();
        }

        gameOverScreen.render();
    }

    @Override
    public void hide() {
        scoreManager.dispose();
    }
}
