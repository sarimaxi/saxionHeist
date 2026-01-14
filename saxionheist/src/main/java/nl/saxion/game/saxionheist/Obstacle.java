package nl.saxion.game.saxionheist;

public class Obstacle {
    protected float x;
    protected float y;

    protected final ObstacleManager manager;

    public Obstacle(ObstacleManager manager, float x, float y) {
        this.manager = manager;
        this.x = x;
        this.y = y;
    }

    public void render(float delta) {
        x -= ScoreManager.currentSpeed * delta;
    }

    public float getX() {
        return x;
    }
}
