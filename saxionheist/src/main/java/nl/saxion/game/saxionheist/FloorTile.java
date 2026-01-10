package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class FloorTile extends Obstacle {
    private final int width = 64;
    private final int height = 32;

    public FloorTile(ObstacleManager manager, float x, float y, String textureName) {
        super(manager, x, y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, width, height, "gray-600");
        GameApp.endShapeRendering();
    }

    public float getRightSide() {
        return x + width;
    }
}