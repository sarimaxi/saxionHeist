package nl.saxion.game.saxionheist.obstaclesets;

import nl.saxion.game.saxionheist.ObstacleManager;
import nl.saxion.game.saxionheist.ObstacleSet;
import nl.saxion.game.saxionheist.SlideObstacle;
import nl.saxion.gameapp.GameApp;

public class SingleSlide extends ObstacleSet {
    public SingleSlide(ObstacleManager obstacleManager) {
        super(obstacleManager);
    }

    @Override
    public void start() {
        super.start();

        prepareObstacle(new SlideObstacle(obstacleManager, GameApp.getWorldWidth(), obstacleManager.getGroundY()), 0f);
    }
}
