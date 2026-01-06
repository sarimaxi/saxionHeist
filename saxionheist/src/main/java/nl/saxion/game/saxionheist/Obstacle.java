package nl.saxion.game.saxionheist;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;

public class Obstacle {
    protected float x;
    protected float y;

    protected final ObstacleManager manager;
    private float speed = 250.0f;

    public Obstacle(ObstacleManager manager, float x, float y) {
        this.manager = manager;
        this.x = x;
        this.y = y;
    }

    public void render(float delta) {
        x -= speed * delta;

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32, Color.RED);
        GameApp.endShapeRendering();

    }

    public float getX() {
        return x;
    }
}
