package nl.saxion.game.saxionheist.obstaclesets;

import nl.saxion.game.saxionheist.JumpObstacle;
import nl.saxion.game.saxionheist.ObstacleManager;
import nl.saxion.game.saxionheist.ObstacleSet;
import nl.saxion.gameapp.GameApp;

public class SingleJump extends ObstacleSet {
    public SingleJump(ObstacleManager obstacleManager) {
        super(obstacleManager);
    }

    @Override
    public void start() {
        super.start();

        prepareObstacle(new JumpObstacle(obstacleManager.player, GameApp.getWorldWidth(),300),0f);
    }
}
