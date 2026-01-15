package nl.saxion.game.saxionheist;

import nl.saxion.game.saxionheist.data.TemporaryData;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class YourGameScreen extends ScalableGameScreen {
    TempBackground background;

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
        background = new TempBackground();
        player = TemporaryData.getCharacter();
        player.x = 300;
        player.y = 400;

        scoreManager = new ScoreManager();
        healthManager = new HealthManager(player.getMaxHealth());
        obstacleManager = new ObstacleManager(player, healthManager);

        gameOverScreen = new GameOverScreen(scoreManager, healthManager, player, obstacleManager);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear
        GameApp.clearScreen();
        background.render();

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