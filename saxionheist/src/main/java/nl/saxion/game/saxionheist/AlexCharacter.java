package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class AlexCharacter extends Player {
    private final String textureName = "alex_run";
    private final String slideTextureName = "alex_slide";

    public float redness = 0f;
    public float blink = 0f;

    public AlexCharacter() {
        super();
        setup();
    }

    public AlexCharacter(float x, float y) {
        super(x, y);
        setup();
    }

    private void setup() {
        maxHealth = 5;

        if (!GameApp.hasTexture(textureName))
            GameApp.addTexture(textureName, "alex-character/AlexandraPixel.png");
        if (!GameApp.hasTexture(slideTextureName))
            GameApp.addTexture(slideTextureName, "alex-character/AlexandraSlide.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startSpriteRendering();
        GameApp.enableTransparency();

        if (state == STATES.SLIDING)
            draw(slideTextureName, 1f, 1f - 0.8f * redness, 1f - 0.8f * redness, 1f - blink);
        else
            draw(textureName, 1f, 1f - 0.8f * redness, 1f - 0.8f * redness, 1f - blink);

        GameApp.disableTransparency();
        GameApp.endSpriteRendering();
    }
}
