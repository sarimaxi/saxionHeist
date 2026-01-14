package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class JumpObstacle extends Obstacle {

    private static final String TEX = "bush";
    private final float w = 70f;
    private final float h = 200f;

    public JumpObstacle(ObstacleManager manager, float x, float y) {
        super(manager, x, y);

        if (!GameApp.hasTexture(TEX))
            GameApp.addTexture(TEX, "jumpObstacle/bush.png");
    }

    @Override
    public void render(float delta) {
        x -= 250.0f * delta;

        GameApp.startSpriteRendering();
        GameApp.drawTexture(TEX, x, y, w, h);
        GameApp.endSpriteRendering();

        if (checkCollision(manager.player))
            manager.health.damage(1);
    }


   public boolean checkCollision(Player player) {
       return GameApp.rectOverlap(x, y, w, h, player.x, player.y, player.width, player.currentHeight);

    }
}

