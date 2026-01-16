package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class SlideObstacle extends Obstacle {
    private static final String TEX = "letter";

    public SlideObstacle(ObstacleManager manager, float x, float y) {
        super(manager, x,  y);

        if (!GameApp.hasTexture(TEX))
            GameApp.addTexture(TEX, "jumpObstacle/slideObstacle.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startSpriteRendering();
        GameApp.drawTexture(TEX, x, y + 48, 128, 128);
        GameApp.endSpriteRendering();

//        GameApp.drawRect(x , y + 100, 32, 32,"green-500");

        if (checkCollision(manager.player) && !manager.player.isSliding){
            manager.health.damage(1, 2);
        }
    }

   public boolean checkCollision(Player player) {
       return GameApp.rectOverlap(x, y, 32, 64, player.x, player.y, 48, player.currentHeight);
    }
}
