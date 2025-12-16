
package nl.saxion.game.saxionheist;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;
import com.badlogic.gdx.math.Rectangle;

public class SlideObstacle extends Obstacle {
        private final float width = 32;
        private final float height = 20; // LOW → slide under
        Player player;


        public SlideObstacle(Player player, float x, float y) {
            super(x,y);
            this.player = player;
            this.speed = 250;
        }

         @Override
        public void update(float delta) {
                x -= speed * delta;
        }

        @Override
        public void render(float delta) {
                super.render(delta);

                // MOVE
                x -= speed * delta;



                // COLLISION
                Rectangle obstacleBox = new Rectangle(x, y, width, height);
                Rectangle playerBox = new Rectangle(
                        player.x,
                        player.y,
                        32,
                        player.currentHeight
                );

                if (obstacleBox.overlaps(playerBox)) {
                        if (!player.isSliding) {
                                player.hitObstacle();
                        }
                }

                // DRAW
                GameApp.startShapeRenderingFilled();
                GameApp.drawRect(x, y, width, height,(Color.BLUE));
                GameApp.endShapeRendering();
        }
}


