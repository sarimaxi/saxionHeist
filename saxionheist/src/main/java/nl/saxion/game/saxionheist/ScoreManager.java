package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class ScoreManager {
    /** Goal to beat level **/
    public int goal = 10000;
    /** Higher values increase score gained **/
    public float modifier = 1f;
    /** Current score **/
    public int score = 0;

    /** Time used to count score **/
    private float time = 0f;
    /** Time since last reset **/
    public float totalTime = 0f;

    /** If true score will update **/
    public boolean processing = true;

    static final String FONTNAME = "score-ui";

    public ScoreManager() {
        if (!GameApp.hasFont(FONTNAME))
            GameApp.addFont(FONTNAME, "fonts/basic.ttf", 50);
    }

    /** Dispose resources **/
    public void dispose() {
        if (GameApp.hasFont(FONTNAME))
            GameApp.disposeFont(FONTNAME);
    }

    /** Update the score and draw it to the screen **/
    public void render(float delta) {
        delta *= (processing ? 1 : 0);
        time += delta;
        totalTime += delta;

        // Calculate and add the amount of points earned since last render call
        int add = (int) Math.floor(time / (1f / modifier));
        score += add;
        time -= add * (1f / modifier);

        // Draw score to screen
        GameApp.startSpriteRendering();
        GameApp.drawText(FONTNAME, "Score: "+score, 0, GameApp.getWorldHeight() - 30,"white");
        GameApp.endSpriteRendering();
    }

    /** Get current score **/
    public float getScore() {
        return score;
    }

    /** Reset current score **/
    public void reset() {
        score = 0;
        modifier = 1f;

        time = 0f;
        totalTime = 0f;
    }

    /** Pause score updates **/
    public void pause() {
        processing = false;
    }

    /** Resume score updates **/
    public void resume() {
        processing = true;
    }
}
