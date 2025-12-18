package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class FloorTile extends Obstacle {

    private final String colorString;
    private final int width = 64;
    private final int height = 32;

    public FloorTile(float x, float y, boolean isAlternate) {
        super(x, y);

        if (isAlternate) {
            this.colorString = "gray-700";
        } else {
            this.colorString = "gray-600";
        }
    }

    @Override
    public void render(float delta) {
        // 1. Move (Copy logic from parent because parent draws RED box)
        x -= 250.0f * delta; // Hardcoded speed matching Obstacle.java

        // 2. Draw Floor (No Collision Logic)
        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, width, height, colorString);
        GameApp.endShapeRendering();
    }

    public float getRightSide() {
        return x + width;
    }
}