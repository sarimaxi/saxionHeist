package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class JumpObstacle extends Obstacle {
    public JumpObstacle(float x, float y) {
        super(x, y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

   public boolean checkCollision(Player player) {
       return GameApp.rectOverlap(
               x, y, 32, 32,
                player.x, player.y, 32, 32
        );
    }
}
