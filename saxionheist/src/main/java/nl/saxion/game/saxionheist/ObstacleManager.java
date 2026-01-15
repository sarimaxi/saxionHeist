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

    public ArrayList<Obstacle> obstacles;

    public Player player;
    public HealthManager health;

    private ObstacleSet currentObstacleSet;

    private float lastFloorX = -100;
    private final float groundY = Player.floorHeight;

    public ObstacleManager(Player player, HealthManager health) {
        this.obstacles = new ArrayList<>();
        this.player = player;
        this.health = health;

        setCurrentObstacleSet(getRandomObstacleSet());

        fillFloorInitially();
    }

    public void render(float delta) {
        if (!currentObstacleSet.isActive())
            setCurrentObstacleSet(getRandomObstacleSet());

        currentObstacleSet.render(delta);

        updateFloor();

        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obs = obstacles.get(i);
            obs.render(delta);

            if (obs.getX() < -100)
                obstacles.remove(i);
        }
    }

    public void reset() {
        if (currentObstacleSet != null)
            currentObstacleSet.end();

        obstacles.clear();
        lastFloorX = -100;

        fillFloorInitially();
        setCurrentObstacleSet(getRandomObstacleSet());
    }

    public float getGroundY() {
        return groundY;
    }

    private void fillFloorInitially() {
        float currentX = -100;
        while (currentX < 1400) {
            addFloorTile(currentX);
            currentX += 64;
        }

        lastFloorX = currentX - 64;
    }

    private void updateFloor() {
        if (lastFloorX < 1280) {
            lastFloorX += 64;
            addFloorTile(lastFloorX);
        }

        lastFloorX -= ScoreManager.currentSpeed * GameApp.getDeltaTime();
    }

    private void addFloorTile(float x) {
        obstacles.add(new FloorTile(this, x, groundY, "road"));
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


