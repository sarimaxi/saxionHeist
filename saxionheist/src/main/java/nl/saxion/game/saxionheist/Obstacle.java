package nl.saxion.game.saxionheist;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;

public class Obstacle {
    private float x;
    private float y;
    private float speed = 250.0f;

    public Obstacle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void render(float delta) {
        x -= speed * delta;

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32, Color.RED);
        GameApp.endShapeRendering();

    }
}
