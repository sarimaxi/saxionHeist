package nl.saxion.game.saxionheist;
import java.util.ArrayList;


public class ObstacleManager {
    // The list of all active obstacles
    private ArrayList<Obstacle> obstacles;

    // Spawn
    private float timer = 0;
    private float nextSpawnTime = 0;

    public ObstacleManager() {
        this.obstacles = new ArrayList<>();
        // one obstacle immediately
        spawnObstacle();
        setNextSpawnTime();
    }

    /**
     * Main update loop: Handles spawning, moving, and drawing.
     */
    public void render(float delta) {

        timer += delta;

        // If it's time to spawn
        if (timer >= nextSpawnTime) {
            spawnObstacle();
            timer = 0;
            setNextSpawnTime();
        }

        // Iterate backwards so we can safely remove items
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obs = obstacles.get(i);

            obs.render(delta); // Move and Draw

            // Remove if off-screen (saves memory)
            if (obs.getX() < -100) {
                obstacles.remove(i);
            }
        }
    }

    /**
     * Logic to decide WHICH obstacle to spawn
     */
    private void spawnObstacle() {
        // 50% chance for ground obstacles, 50% for flying obstacles
        if (Math.random() < 0.5) {
            obstacles.add(new Obstacle(1280, 300));
        } else {
            // Random height 300 - 500
            float randomY = 300 + (float)(Math.random() * 200);
            obstacles.add(new Obstacle(1280, randomY));
        }
    }

    /**
     * Logic to decide WHEN to spawn next
     */
    private void setNextSpawnTime() {
        nextSpawnTime = 1.0f + (float)(Math.random() * 2.0f);
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

}
