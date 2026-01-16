package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class PujaCharacter extends Player {
    private final String textureName = "puja_runjump";
    private final String slideTextureName = "puja_slide";

    private final String dashTextureName = "puja_dash";
    public PujaCharacter() {
        super();
        setup();
    }

    public PujaCharacter(float x, float y) {
        super(x, y);
        setup();
    }

    public void setup() {
        maxHealth = 5;

        if (!GameApp.hasTexture(textureName))
            GameApp.addTexture(textureName, "puja-character/puja-run.png");
        if (!GameApp.hasTexture(slideTextureName))
            GameApp.addTexture(slideTextureName, "puja-character/puja-bend.png");

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.startSpriteRendering();


        if (state == STATES.SLIDING) {
            GameApp.drawTexture(slideTextureName, x, y - 35, width, currentHeight);
        }
        else {
            GameApp.drawTexture(textureName, x, y - 35, width, currentHeight);
        }

        GameApp.endSpriteRendering();
    }
}

