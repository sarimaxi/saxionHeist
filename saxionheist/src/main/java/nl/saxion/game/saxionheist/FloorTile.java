package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class FloorTile extends Obstacle {

    private static boolean tint = false;
    private final boolean tinted;
    private final int width = 64;
    private final int height;

    public FloorTile(ObstacleManager manager, float x, float y, String textureName) {
        super(manager, x, y);

        // HACK DO NOT JUDGE ME FOR THIS, THIS ENTIRE PROJECT IS DUE TOMORROW AND IT'S 21:10 RN
        tinted = tint;
        tint = !tint;
        height = (int)y;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y - height, width, height, tinted ? "gray-600" : "gray-400");
        GameApp.endShapeRendering();
    }

    public float getRightSide() {
        return x + width;
    }
}