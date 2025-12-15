
package nl.saxion.game.saxionheist;
import nl.saxion.gameapp.GameApp;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class SlideObstacle {

        private float x;
        private float y;
        private float speed = 250.0f;

        private final float width = 32;
        private final float height = 20; // LOW → slide under

        public SlideObstacle(float x, float y) {
                this.x = x;
                this.y = y;
        }

        public void render(float delta, Player player) {
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
                GameApp.drawRect(x, y, width, height, String.valueOf(Color.BLUE));
                GameApp.endShapeRendering();
        }
}


