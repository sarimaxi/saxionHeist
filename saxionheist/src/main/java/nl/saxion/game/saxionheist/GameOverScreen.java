package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

import java.util.ArrayList;

public class GameOverScreen {

    private boolean active = false;
    private boolean newHighscore = false;
    private final ScoreManager score;
    private final HealthManager health;
    private final Player player;
    private final ObstacleManager obstacles;

    private static final int width = 1280, height = 720;

    // IMPORTANT: use a DIFFERENT texture key than the menu, otherwise it reuses the old one
    private static final String BG = "gameover_bg";
    private static final String MONEY = "money_particle";

    private float time = 0f;

    private static class MoneyBill {
        float x, y;
        float speed;
        float size;
        float sway;
        float swaySpeed;
    }

    private final ArrayList<MoneyBill> bills = new ArrayList<>();

    public GameOverScreen(ScoreManager score, HealthManager health, Player player, ObstacleManager obstacles) {
        this.score = score;
        this.health = health;
        this.player = player;
        this.obstacles = obstacles;

        if (!GameApp.hasSound("gameover"))
            GameApp.addSound("gameover", "sounds/gameover.wav");

        // Fonts used for game over UI
        if (!GameApp.hasFont("basic"))
            GameApp.addFont("basic", "fonts/basic.ttf", 64);

        // Game over background (red one)
        if (!GameApp.hasTexture(BG))
            GameApp.addTexture(BG, "gameOverBackground.png");

        // Money particle texture (same as menu)
        if (!GameApp.hasTexture(MONEY))
            GameApp.addTexture(MONEY, "money.png");

        resetBills();
    }

    private void resetBills() {
        bills.clear();
        time = 0f;

        // bigger + fewer
        for (int i = 0; i < 12; i++) {
            MoneyBill b = new MoneyBill();
            b.x = (float) (Math.random() * width);
            b.y = (float) (Math.random() * height);
            b.speed = 12f + (float) (Math.random() * 16f);
            b.size = 80f + (float) (Math.random() * 40f);
            b.sway = 10f + (float) (Math.random() * 16f);
            b.swaySpeed = 0.8f + (float) (Math.random() * 1.2f);
            bills.add(b);
        }
    }

    public void show() {
        active = true;
        score.pause();

        if (score.score > score.getHighScore()) {
            score.setHighScore(score.score);
            newHighscore = true;
        }

        GameApp.playSound("gameover");

        // refresh particles every death
        resetBills();
    }

    public void render() {
        if (!active) return;

        // Background
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();
        GameApp.drawTexture(BG, 0, 0, width, height);
        GameApp.endSpriteRendering();

        // Particles
        renderMoney(GameApp.getDeltaTime());

        // UI
        GameApp.startSpriteRendering();

        GameApp.drawTextCentered(
                "basic",
                "GAME OVER",
                GameApp.getWorldWidth() / 2,
                GameApp.getWorldHeight() / 2 + 120,
                "white"
        );

        if (newHighscore) {
            GameApp.drawTextCentered(
                    "basic",
                    "New high score: " + score.getHighScore() + "!",
                    GameApp.getWorldWidth() / 2,
                    GameApp.getWorldHeight() / 2 + 40,
                    "white"
            );
        } else {
            GameApp.drawTextCentered(
                    "basic",
                    "Your score: " + score.score + "  HS: " + score.getHighScore(),
                    GameApp.getWorldWidth() / 2,
                    GameApp.getWorldHeight() / 2 + 40,
                    "white"
            );
        }

        GameApp.drawTextCentered(
                "basic",
                "Press R to return to character select",
                GameApp.getWorldWidth() / 2,
                GameApp.getWorldHeight() / 2 - 60,
                "white"
        );

        GameApp.endSpriteRendering();

        if (GameApp.isKeyJustPressed(Input.Keys.R)) {
            retry();
        }
    }

    private void renderMoney(float delta) {
        GameApp.startSpriteRendering();

        time += delta;

        for (MoneyBill b : bills) {
            b.y -= b.speed * delta;

            float drift = (float) Math.sin(time * b.swaySpeed + b.x * 0.01f) * b.sway;

            if (b.y < -120) {
                b.y = height + 120;
                b.x = (float) (Math.random() * width);
            }

            GameApp.drawTexture(MONEY, b.x + drift, b.y, b.size, b.size);
        }

        GameApp.endSpriteRendering();
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






