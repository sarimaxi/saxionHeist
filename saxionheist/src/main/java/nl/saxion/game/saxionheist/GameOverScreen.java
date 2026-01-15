package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class GameOverScreen {

    private boolean active = false;
    private boolean newHighscore = false;
    private ScoreManager score;
    private HealthManager health;
    private Player player;
    private ObstacleManager obstacles;

    public GameOverScreen(ScoreManager score, HealthManager health, Player player, ObstacleManager obstacles) {
        this.score = score;
        this.health = health;
        this.player = player;
        this.obstacles = obstacles;

        if(!GameApp.hasSound("gameover"))
            GameApp.addSound("gameover", "sounds/gameover.wav");

        // ✅ Make sure the font used below actually exists
        if (!GameApp.hasFont("basic"))
            GameApp.addFont("basic", "fonts/basic.ttf", 64);
    }

    public void show() {
        active = true;
        score.pause();

        if (score.score > score.getHighScore()) {
            score.setHighScore(score.score);
            newHighscore = true;
        }

        GameApp.playSound("gameover");
    }

    public void render() {
        if (!active) return;

        GameApp.startSpriteRendering();

        if (newHighscore) {
            GameApp.drawTextCentered(
                    "basic",
                    "New high score: " + score.getHighScore() + "!",
                    GameApp.getWorldWidth() / 2,
                    GameApp.getWorldHeight() / 2 + 160,
                    "white"
            );
        } else {
            GameApp.drawTextCentered(
                    "basic",
                    "Your score: " + score.score + " HS: " + score.getHighScore(),
                    GameApp.getWorldWidth() / 2,
                    GameApp.getWorldHeight() / 2 + 160,
                    "white"
            );
        }

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
        newHighscore = false;

        score.reset();
        score.resume();
        health.reset();

        if (obstacles != null)
            obstacles.reset();

        if (player != null)
            player.resetToStart();

        GameApp.switchScreen("CharacterSelectScreen");
    }

    public boolean isActive() {
        return active;
    }
}




