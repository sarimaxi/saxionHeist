package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class SlideObstacle extends Obstacle {
    public SlideObstacle(ObstacleManager manager, float x, float y) {
        super(manager, x, y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y + 32, 32, 32,"green-500");
        GameApp.endShapeRendering();

       if (checkCollision(manager.player) && !manager.player.isSliding)
           manager.health.damage(1);
    }

   public boolean checkCollision(Player player) {
       return GameApp.rectOverlap(x, y, 32, 32 * 8, player.x + 32, player.y, 32, player.currentHeight);
    }
}
