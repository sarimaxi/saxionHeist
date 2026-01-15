package nl.saxion.game.saxionheist;

import nl.saxion.game.saxionheist.data.SaveData;
import nl.saxion.gameapp.GameApp;

public class ScoreManager {
    public int goal = 10000;
    public float modifier = 1f;
    public int score = 0;

    private float time = 0f;
    public float totalTime = 0f;

    public boolean processing = true;

    static final String FONTNAME = "score-ui";

    public static final float BASE_SPEED = 250f;
    public static final float SPEED_INCREASE_PER_SECOND = 8f;
    public static final float MAX_SPEED = 550f;

    public static float currentSpeed = BASE_SPEED;

    public ScoreManager() {
        if (!GameApp.hasFont(FONTNAME))
            GameApp.addFont(FONTNAME, "fonts/basic.ttf", 64);
    }

    public void dispose() {
        if (GameApp.hasFont(FONTNAME))
            GameApp.disposeFont(FONTNAME);
    }

    public void render(float delta) {
        delta *= (processing ? 1 : 0);
        time += delta;
        totalTime += delta;

        currentSpeed = Math.min(BASE_SPEED + totalTime * SPEED_INCREASE_PER_SECOND, MAX_SPEED);

        int add = (int) Math.floor(time / (1f / modifier));
        score += add;
        time -= add * (1f / modifier);

        GameApp.startSpriteRendering();
        GameApp.drawText(FONTNAME, "Score: " + score, 0, GameApp.getWorldHeight() - 30, "black");
        GameApp.endSpriteRendering();
    }

    public float getScore() {
        return score;
    }

    public int getHighScore() {
        SaveData data = null;
        try {
            data = GameApp.loadFromJson("SaveData.json", SaveData.class);
        } catch (Exception ignored) {}

        if (data != null)
            return data.highscore;

        return 0;
    }

    public void setHighScore(int value) {
        SaveData data = null;
        try {
            data = GameApp.loadFromJson("SaveData.json", SaveData.class);
        } catch (Exception ignored) {}

        if (data == null)
            data = new SaveData();

        data.highscore = value;
        GameApp.saveToJson(data, "SaveData.json");
    }

    public void reset() {
        score = 0;
        modifier = 1f;
        time = 0f;
        totalTime = 0f;
        currentSpeed = BASE_SPEED;
    }

    public void pause() {
        processing = false;
    }

    public void resume() {
        processing = true;
    }
}

