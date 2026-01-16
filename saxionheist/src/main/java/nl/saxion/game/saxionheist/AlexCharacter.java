package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class AlexCharacter extends Player {
    private final String textureName = "alex_run";
    private final String slideTextureName = "alex_slide";

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

        if (state == STATES.SLIDING)
            GameApp.drawTexture(slideTextureName, x, y - 35, 200, currentHeight);
        else
            GameApp.drawTexture(textureName, x, y - 35, 200, currentHeight);

        GameApp.endSpriteRendering();
    }
}
