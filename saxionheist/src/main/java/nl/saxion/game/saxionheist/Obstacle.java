package nl.saxion.game.saxionheist;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;

public abstract class Obstacle {
    protected float x;
    protected float y;
    protected float speed = 250.0f;

    public Obstacle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(float delta);

    public void render(float delta) {
        x -= speed * delta;
    }
}
