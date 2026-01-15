package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import nl.saxion.gameapp.GameApp;

public class Button {
    public float x, y, width, height;
    public String text;
    public String font = "ButtonFont";

    private static final int mouseMargin = 1;

    private boolean hovered = false;

    private static final String COLOR_NORMAL = "saxionGreenBtn";
    private static final String COLOR_HOVER = "saxionGreenBtnHover";

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
            GameApp.addFont("ButtonFont", "fonts/basic.ttf", 48);

        if (!GameApp.hasFont("TitleFont"))
            GameApp.addFont("TitleFont", "fonts/basic.ttf", 110);

        if (!GameApp.hasFont("SubFont"))
            GameApp.addFont("SubFont", "fonts/basic.ttf", 56);

        if (!GameApp.hasFont("SmallFont"))
            GameApp.addFont("SmallFont", "fonts/basic.ttf", 34);

        if (!GameApp.hasSound("ButtonSfx"))
            GameApp.addSound("ButtonSfx", "sounds/button.wav");

        if (!GameApp.hasColor(COLOR_NORMAL))
            GameApp.addColor(COLOR_NORMAL, 0, 166, 81, 150);

        if (!GameApp.hasColor(COLOR_HOVER))
            GameApp.addColor(COLOR_HOVER, 0, 220, 110, 210);
    }

    public static void dispose() {
        if (GameApp.hasFont("ButtonFont"))
            GameApp.disposeFont("ButtonFont");

        if (GameApp.hasFont("TitleFont"))
            GameApp.disposeFont("TitleFont");

        if (GameApp.hasFont("SubFont"))
            GameApp.disposeFont("SubFont");

        if (GameApp.hasFont("SmallFont"))
            GameApp.disposeFont("SmallFont");

        if (GameApp.hasSound("ButtonSfx"))
            GameApp.disposeSound("ButtonSfx");
    }

    public boolean isHovered(float worldWidth, float worldHeight) {
        Vector2 mousePos = GameApp.getMousePositionInWindow();
        Vector2 mouseUV = new Vector2(mousePos.x / GameApp.getWindowWidth(), mousePos.y / GameApp.getWindowHeight());

        float mx = mouseUV.x * worldWidth;
        float my = mouseUV.y * worldHeight;

        return GameApp.rectOverlap(
                x - width / 2, y - height / 2, width, height,
                mx - mouseMargin, my - mouseMargin, mouseMargin * 2, mouseMargin * 2
        );
    }

    public void render(float delta, float worldWidth, float worldHeight) {
        hovered = isHovered(worldWidth, worldHeight);

        float s = hovered ? 1.06f : 1.0f;
        float w = width * s;
        float h = height * s;

        GameApp.enableTransparency();
        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x - w / 2, y - h / 2, w, h, hovered ? COLOR_HOVER : COLOR_NORMAL);
        GameApp.endShapeRendering();
        GameApp.disableTransparency();

        GameApp.startSpriteRendering();
        GameApp.drawTextCentered(font, text, x, y, "white");
        GameApp.endSpriteRendering();
    }

    public boolean isPressed(float worldWidth, float worldHeight) {
        if (!hovered)
            hovered = isHovered(worldWidth, worldHeight);

        if (hovered && GameApp.isButtonPressed(Input.Buttons.LEFT)) {
            GameApp.playSound("ButtonSfx");
            return true;
        }

        return false;
    }
}

