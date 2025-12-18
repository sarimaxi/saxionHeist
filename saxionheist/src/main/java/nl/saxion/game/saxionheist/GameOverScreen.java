package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;


public class GameOverScreen {

    private boolean active = false;
    private ScoreManager score;
    private Health health;

    public GameOverScreen(ScoreManager score, Health health) {
        this.score = score;
        this.health = health;

    }

    public void show() {
        active = true;
        score.pause();
    }

    public void render() {
        if (!active) return;

        GameApp.startSpriteRendering();



        GameApp.drawTextCentered(
                "basic",
                "GAME OVER",
                GameApp.getWorldWidth() / 2,
                GameApp.getWorldHeight() / 2 + 80,
                "white"
        );

        GameApp.drawTextCentered(
                "basic",
                "Press R to Retry",
                GameApp.getWorldWidth() / 2,
                GameApp.getWorldHeight() / 2,
                "white"
        );

        GameApp.endSpriteRendering();

        if (GameApp.isKeyJustPressed(Input.Keys.R)) {
            retry();
        }
    }

    private void retry() {
        active = false;
        score.reset();
        score.resume();
        health.reset();
    }

    public boolean isActive() {
        return active;
    }
}


