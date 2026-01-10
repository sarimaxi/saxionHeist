package nl.saxion.game.saxionheist.obstaclesets;

import nl.saxion.game.saxionheist.JumpObstacle;
import nl.saxion.game.saxionheist.ObstacleManager;
import nl.saxion.game.saxionheist.ObstacleSet;
import nl.saxion.gameapp.GameApp;

public class LongJump extends ObstacleSet {
    public LongJump(ObstacleManager obstacleManager) {
        super(obstacleManager);
    }

    @Override
    public void start() {
        super.start();

        prepareObstacle(new JumpObstacle(obstacleManager.player, GameApp.getWorldWidth(),300),0f);
        prepareObstacle(new JumpObstacle(obstacleManager.player, GameApp.getWorldWidth(),300),0.1f);
        prepareObstacle(new JumpObstacle(obstacleManager.player, GameApp.getWorldWidth(),300),0.2f);
        prepareObstacle(new JumpObstacle(obstacleManager.player, GameApp.getWorldWidth(),300),0.3f);
    }
}
