package nl.saxion.game.saxionheist.obstaclesets;

import nl.saxion.game.saxionheist.JumpObstacle;
import nl.saxion.game.saxionheist.ObstacleManager;
import nl.saxion.game.saxionheist.ObstacleSet;
import nl.saxion.gameapp.GameApp;

public class DoubleJump extends ObstacleSet {
    public DoubleJump(ObstacleManager obstacleManager) {
        super(obstacleManager);
    }

    @Override
    public void start() {
        super.start();

        prepareObstacle(new JumpObstacle(obstacleManager, GameApp.getWorldWidth(),obstacleManager.getGroundY() - 55
        ),0f);
        prepareObstacle(new JumpObstacle(obstacleManager, GameApp.getWorldWidth(),obstacleManager.getGroundY() - 55
        ),1f);
    }
}
