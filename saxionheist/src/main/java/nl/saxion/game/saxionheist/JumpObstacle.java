package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

import java.awt.*;

public class JumpObstacle extends Obstacle {
    Player player;

    public JumpObstacle(Player player, float x, float y) {
        super(x, y);
        this.player = player;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32,"red-500");
        GameApp.endShapeRendering();

       if (checkCollision(player)){
        System.out.println("Crash");
       }
    }

   public boolean checkCollision(Player player) {
       return GameApp.rectOverlap(
               x, y, 32, 32,
                player.x, player.y, 32, 32
        );
    }
}
