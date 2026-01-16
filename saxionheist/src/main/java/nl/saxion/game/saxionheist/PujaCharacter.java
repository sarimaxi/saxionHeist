package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class PujaCharacter extends Player {
    private final String textureName = "puja_runjump";
    private final String slideTextureName = "puja_slide";
    private final String dashTextureName = "puja_dash";

    public float redness = 0f;
    public float blink = 0f;

    public PujaCharacter() {
        super();
        setup();
    }

    public PujaCharacter(float x, float y) {
        super(x, y);
        setup();
    }

    public void setup() {
        maxHealth = 3;
        ScoreManager.speedIncrease = ScoreManager.BASE_INCREASE_PER_SECOND * 2;

        if (!GameApp.hasTexture(textureName))
            GameApp.addTexture(textureName, "puja-character/puja-run.png");
        if (!GameApp.hasTexture(slideTextureName))
            GameApp.addTexture(slideTextureName, "puja-character/puja-bend.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startSpriteRendering();

        if (state == STATES.SLIDING)
            draw(slideTextureName, 1f, 1f - 0.8f * redness, 1f - 0.8f * redness, 1f - blink);
        else
            draw(textureName, 1f, 1f - 0.8f * redness, 1f - 0.8f * redness, 1f - blink);

        GameApp.endSpriteRendering();
    }
}

