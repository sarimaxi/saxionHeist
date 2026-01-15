package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class NancyCharacter extends Player {

    private final String textureName = "nancy_run";
    private final String slideTextureName = "nancy_slide";

    public NancyCharacter() {
        super();
        setup();
    }

    public NancyCharacter(float x, float y) {
        super(x, y);
        setup();
    }

    private void setup() {
        maxHealth = 5;

        if (!GameApp.hasTexture(textureName))
            GameApp.addTexture(textureName, "nancy-character/NancyPixel.png");
        if (!GameApp.hasTexture(slideTextureName))
            GameApp.addTexture(slideTextureName, "nancy-character/NancySlide.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startSpriteRendering();

        if (state == STATES.SLIDING)
            GameApp.drawTexture(slideTextureName, x, y - 48, 200, currentHeight);
        else
            GameApp.drawTexture(textureName, x, y - 48, 200, currentHeight);

        GameApp.endSpriteRendering();
    }
}


