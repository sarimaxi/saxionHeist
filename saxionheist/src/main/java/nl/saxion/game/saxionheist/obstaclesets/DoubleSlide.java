package nl.saxion.game.saxionheist.obstaclesets;

import nl.saxion.game.saxionheist.ObstacleManager;
import nl.saxion.game.saxionheist.ObstacleSet;
import nl.saxion.game.saxionheist.SlideObstacle;
import nl.saxion.gameapp.GameApp;

public class DoubleSlide extends ObstacleSet {
    public DoubleSlide(ObstacleManager obstacleManager) {
        super(obstacleManager);
    }

    @Override
    public void start() {
        super.start();

        prepareObstacle(new SlideObstacle(obstacleManager, GameApp.getWorldWidth(), obstacleManager.getGroundY()), 0f);
        prepareObstacle(new SlideObstacle(obstacleManager, GameApp.getWorldWidth(), obstacleManager.getGroundY()), 1f);
    }
}
