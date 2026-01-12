package nl.saxion.game.saxionheist;

import nl.saxion.game.saxionheist.obstaclesets.*;
import nl.saxion.gameapp.GameApp;

import java.util.ArrayList;

public class ObstacleManager {
    final private ObstacleSet[] obstacleSets = {
            new SingleJump(this),
            new LongJump(this),
            new DoubleJump(this),
            new SingleSlide(this),
            new DoubleSlide(this),
    };

    //The list of all active obstacles
    public ArrayList<Obstacle> obstacles;

    //Reference to the player
    public Player player;
    public HealthManager health;

    private ObstacleSet currentObstacleSet;

    private float lastFloorX = -100;
    private final float floorY = 300 - 32;


    public ObstacleManager(Player player, HealthManager health) {
        this.obstacles = new ArrayList<>();
        this.player = player;
        this.health = health;

        setCurrentObstacleSet(getRandomObstacleSet());

        fillFloorInitially();
    }

    //Main update loop: Handles spawning, moving, and drawing.
    public void render(float delta) {
        // Pick new set if current set has finished
        if (!currentObstacleSet.isActive())
            setCurrentObstacleSet(getRandomObstacleSet());

        // Update set
        currentObstacleSet.render(delta);

        updateFloor();

        // Iterate backwards so we can safely remove items
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obs = obstacles.get(i);
            obs.render(delta); // Move and Draw

            // Remove if off-screen (saves memory)
            if (obs.getX() < -100)
                obstacles.remove(i);
        }
    }


    private void fillFloorInitially() {
        float currentX = -100;
        while (currentX < 1400) {
            addFloorTile(currentX);
            currentX += 64; // Width of tile
        }

        lastFloorX = currentX - 64;
    }

    private void updateFloor() {
        if (lastFloorX < 1280) {
            lastFloorX += 64; // Move spawn point to the right
            addFloorTile(lastFloorX);
        }

        lastFloorX -= 250.0f * GameApp.getDeltaTime();
    }

    private void addFloorTile(float x) {
        obstacles.add(new FloorTile(this, x, floorY, "road"));
    }

    private ObstacleSet getRandomObstacleSet() {
        return obstacleSets[(int) (Math.random() * obstacleSets.length)];
    }

    private void setCurrentObstacleSet(ObstacleSet newObstacleSet) {
        currentObstacleSet = newObstacleSet;
        currentObstacleSet.start();
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
