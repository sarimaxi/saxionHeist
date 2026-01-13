package nl.saxion.game.saxionheist;

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

    List<ParallaxLayer> backgroundLayers = new ArrayList<>();

    // Filenames and scroll speeds for the layers
    private final String[] layerFiles = {
            "blueSky.png",
            "sun.png",
            "mountainPath.png",
            "bigClouds.png",
            "smallClouds.png",
            "valleyPath.png",
            "grassField.png",
            "bushPath.png"
    };
    private final float[] layerSpeeds = {0.2f, 0.1f, 0.4f, 0.6f, 0.8f, 1.5f, 1.0f, 1.2f};

    public YourGameScreen() {
        super(1280, 720); // world width and height
    }

    @Override
    public void show() {
        // Initialize player and managers
        player = new Player("String", 300, 400);
        scoreManager = new ScoreManager();
        healthManager = new HealthManager(3);
        obstacleManager = new ObstacleManager(player, healthManager);
        gameOverScreen = new GameOverScreen(scoreManager, healthManager);

        // Load all background layers
        for (int i = 0; i < layerFiles.length; i++) {
            backgroundLayers.add(new ParallaxLayer(layerFiles[i], layerSpeeds[i]));
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen();

        if (!gameOverScreen.isActive()) {
            // Render all background layers
            for (ParallaxLayer layer : backgroundLayers) {
                layer.render(delta);
            }

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

    // Inner class for parallax layers
    private class ParallaxLayer {
        private final String filename; // resource filename
        private final float speed;     // scroll speed
        private float xOffset;         // current offset

        public ParallaxLayer(String filename, float speed) {
            this.filename = filename;
            this.speed = speed;
            this.xOffset = 0;
        }

        public void render(float delta) {
            // Move layer
            xOffset -= speed * delta * 100;

            // Repeat using world width
            float worldWidth = YourGameScreen.this.getWorldWidth();
            if (xOffset <= -worldWidth) xOffset += worldWidth;

            // Draw the texture twice for seamless scrolling
            GameApp.drawTexture(filename, xOffset, 0);
            GameApp.drawTexture(filename, xOffset + worldWidth, 0);
        }
    }
}
