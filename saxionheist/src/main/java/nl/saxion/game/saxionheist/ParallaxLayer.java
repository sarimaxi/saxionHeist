package nl.saxion.game.saxionheist;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import nl.saxion.gameapp.GameApp;

public class ParallaxLayer {
    private final String filename; // resource filename
    private final float speed;     // scroll speed

    private final float scale = 4;
    private float x1, x2, y = 0f;

    private boolean stationary = false;

    // HACK I know this is a really dumb way of adding a stationary layer but you don't really care do you now ;)
    public ParallaxLayer(String filename, Vector2 pos) {
        this.filename = filename;
        this.speed = 0f;
        this.x1 = pos.x;
        this.y = pos.y;

        stationary = true;
    }

    public ParallaxLayer(String filename, float speed) {
        this.filename = filename;
        this.speed = speed;
    }

    public ParallaxLayer(String filename, float speed, float y) {
        this.filename = filename;
        this.speed = speed;
        this.y = y;
    }

    public void init() {
        if(GameApp.hasTexture(filename)) return;
        GameApp.addTexture(filename, filename, Texture.TextureFilter.Nearest);

        if (stationary) return;
        x1 = 0;
        x2 = GameApp.getTextureWidth(filename) * scale;
    }

    public void dispose() {
        if(GameApp.hasTexture(filename))
            GameApp.disposeTexture(filename);
    }

    public void render(float delta) {
        if (stationary) {
            GameApp.startSpriteRendering();
            GameApp.drawTexture(filename, x1, y, getWidth(), getHeight());
            GameApp.endSpriteRendering();
            return;
        }

        x1 -= speed * delta * 150;
        x2 -= speed * delta * 150;

        GameApp.startSpriteRendering();

        // Draw the texture twice for seamless scrolling
        GameApp.drawTexture(filename, x1, y, getWidth(), getHeight());
        GameApp.drawTexture(filename, x2, y, getWidth(), getHeight());

        if (x1 <= -getWidth())
            x1 += getWidth() * 2;

        if (x2 <= -getWidth())
            x2 += getWidth() * 2;

        GameApp.endSpriteRendering();
    }

    private float getWidth() {
        return GameApp.getTextureWidth(filename) * scale;
    }
    private float getHeight() {
        return GameApp.getTextureHeight(filename) * scale;
    }
}