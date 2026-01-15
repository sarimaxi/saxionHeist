package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import nl.saxion.game.saxionheist.data.TemporaryData;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;
import java.util.List;

public class YourGameScreen extends ScalableGameScreen {
    Player player;
    ScoreManager scoreManager;
    HealthManager healthManager;
    ObstacleManager obstacleManager;
    GameOverScreen gameOverScreen;

    // Filenames and scroll speeds for the layers
    private final ParallaxLayer[] backgroundLayers = {
            new ParallaxLayer("Assets/blueSky.png", 0.1f, 128),
            new ParallaxLayer("Assets/sun.png", new Vector2(1024,512)), // Warcrime aha stationary layer implementation :) I just want this to be done ok?
            new ParallaxLayer("Assets/bigClouds.png", 0.4f, 510),
            new ParallaxLayer("Assets/bushPath.png", 0.6f, 251),
            new ParallaxLayer("Assets/smallClouds.png", 0.8f, 510),
            new ParallaxLayer("Assets/grassField.png", 1.0f, -192),
            new ParallaxLayer("Assets/valleyPath.png", 1.5f)
    };

    public YourGameScreen() {
        super(1280, 720); // world width and height
    }

    @Override
    public void show() {
        player = TemporaryData.getCharacter();
        scoreManager = new ScoreManager();
        healthManager = new HealthManager(player.getMaxHealth());
        obstacleManager = new ObstacleManager(player, healthManager);
        gameOverScreen = new GameOverScreen(scoreManager, healthManager);

        for (ParallaxLayer layer : backgroundLayers)
            layer.init();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen();

        if (!gameOverScreen.isActive()) {
            // Render all background layers
            for (ParallaxLayer layer : backgroundLayers)
                layer.render(delta);

            // Render game objects
            player.render(delta);
            obstacleManager.render(delta);
            scoreManager.render(delta);
            healthManager.render(delta);

            // Check for death
            if (healthManager.isDead()) gameOverScreen.show();
        }

        // Render game over screen on top
        gameOverScreen.render();
    }

    @Override
    public void hide() {
        // Dispose of resources
        scoreManager.dispose();
    }


}
