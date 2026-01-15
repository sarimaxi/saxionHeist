package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import nl.saxion.gameapp.GameApp;

public class Button {
    public float x, y, width, height;
    public String text;
    public String font = "ButtonFont";

    private static final int mouseMargin = 1;

    public Button(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Button(float x, float y, float width, float height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public static void init() {
        if (!GameApp.hasFont("ButtonFont"))
            GameApp.addFont("ButtonFont", "fonts/basic.ttf", 40);

        if (!GameApp.hasFont("ButtonSfx"))
            GameApp.addSound("ButtonSfx", "sounds/button.wav");
    }

    public static void dispose() {
        if (GameApp.hasFont("ButtonFont"))
            GameApp.disposeFont("ButtonFont");

        if (GameApp.hasFont("ButtonSfx"))
            GameApp.disposeSound("ButtonSfx");
    }

    public void render(float delta) {
        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x - width / 2, y - height / 2, width, height, "amber-200");
        GameApp.endShapeRendering();

        GameApp.startSpriteRendering();
        GameApp.drawTextCentered(font, text, x, y, "black");
        GameApp.endSpriteRendering();
    }

    public boolean isPressed(Vector2 worldSize) {
        return isPressed(worldSize.x, worldSize.y);
    }

    public boolean isPressed(float worldWidth, float worldHeight) {
        Vector2 mousePos = GameApp.getMousePositionInWindow();
        Vector2 mouseUV =  new Vector2(mousePos.x / GameApp.getWindowWidth(),mousePos.y /  GameApp.getWindowHeight());

        if (GameApp.rectOverlap(x - width / 2, y - height / 2, width, height,mouseUV.x * worldWidth - mouseMargin,mouseUV.y * worldHeight - mouseMargin,mouseMargin * 2,mouseMargin * 2) &&
            GameApp.isButtonPressed(Input.Buttons.LEFT)) {

            GameApp.playSound("ButtonSfx");
            return true;
        }

        return false;

    }
}
