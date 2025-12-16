package nl.saxion.game.saxionheist;

import java.util.ArrayList;

public abstract class ObstacleSet {
    /** Obstacle in queue **/
    private class QueuedObstacle {
        final public Obstacle obstacle;
        final public float delay;

        public QueuedObstacle(Obstacle obstacle, float delay) {
            this.obstacle = obstacle;
            this.delay = delay;
        }
    }

    private final ArrayList<QueuedObstacle> queue = new ArrayList<>();
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private float lifeTime = 0f;

    protected ObstacleManager obstacleManager = null;
    protected boolean active = false;

    public ObstacleSet(ObstacleManager obstacleManager) {
        this.obstacleManager = obstacleManager;
    }

    public void start() {
        active = true;
        lifeTime = 0f;
    }

    public void end() {
        obstacles.clear();
        active = false;
        lifeTime = 0f;
    }

    public void render(float delta) {
        if (!active) return;
        lifeTime += delta;

        // Check queued obstacles
        for (int i = queue.size() - 1; i >= 0; i--) {
            if (queue.get(i).delay <= lifeTime) {
                // Spawn obstacle from queue
                obstacles.add(queue.get(i).obstacle);
                obstacleManager.obstacles.add(queue.get(i).obstacle);
                queue.remove(i);
            }
        }

        // End this set
        if (queue.isEmpty() && getHighestX() < 200)
            end();
    }

    public boolean isActive() {
        return this.active;
    }

    public float getHighestX() {
        float h = Float.NEGATIVE_INFINITY;

        for (Obstacle obstacle : obstacles)
            h = Math.max(h,obstacle.getX());

        return h;
    }

    protected void prepareObstacle(Obstacle obstacle, float delay) {
        queue.add(new QueuedObstacle(obstacle, delay));
    }
}