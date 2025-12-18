package nl.saxion.game.saxionheist;

import nl.saxion.game.saxionheist.obstaclesets.*;
import java.util.ArrayList;

public class ObstacleManager {
    final private ObstacleSet[] obstacleSets = {
            new SingleJump(this),
            new LongJump(this),
            new DoubleJump(this)
    };

    /** The list of all active obstacles **/
    public ArrayList<Obstacle> obstacles;
    /** Reference to the player **/
    public Player player;

    private ObstacleSet currentObstacleSet;

    public ObstacleManager(Player player) {
        this.obstacles = new ArrayList<>();
        this.player = player;

        setCurrentObstacleSet(getRandomObstacleSet());
    }

    /**
     * Main update loop: Handles spawning, moving, and drawing.
     */
    public void render(float delta) {
        // Pick new set if current set has finished
        if (!currentObstacleSet.isActive())
            setCurrentObstacleSet(getRandomObstacleSet());

        // Update set
        currentObstacleSet.render(delta);

        // Iterate backwards so we can safely remove items
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obs = obstacles.get(i);
            obs.render(delta); // Move and Draw

            // Remove if off-screen (saves memory)
            if (obs.getX() < -100)
                obstacles.remove(i);
        }
    }

    private ObstacleSet getRandomObstacleSet() {
        return obstacleSets[(int)(Math.random() * obstacleSets.length)];
    }

    private void setCurrentObstacleSet(ObstacleSet newObstacleSet) {
        currentObstacleSet = newObstacleSet;
        currentObstacleSet.start();
    }
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
