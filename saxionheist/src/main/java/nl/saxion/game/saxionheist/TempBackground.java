package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class TempBackground {

    private static final String KEY = "temp_background";

    public TempBackground() {
        if (!GameApp.hasTexture(KEY)) {
            GameApp.addTexture(KEY, "tempBackground/background.jpg");
        }
    }

    public void render() {
        GameApp.startSpriteRendering();
        GameApp.drawTexture(
                KEY,
                0,
                0,
                GameApp.getWorldWidth(),
                GameApp.getWorldHeight()
        );
        GameApp.endSpriteRendering();
    }
}
