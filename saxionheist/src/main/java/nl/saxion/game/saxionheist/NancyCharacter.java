package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class NancyCharacter extends Player {

    private final String textureName = "nancy_run";
    private final String slideTextureName = "nancy_slide";

    private int jumpsLeft = 2; // Nancy can jump twice before landing

    public NancyCharacter() {
        super();
        setup();
    }

    public NancyCharacter(float x, float y) {
        super(x, y);
        setup();
    }

    private void setup() {
        maxHealth = 3;

        if (!GameApp.hasTexture(textureName))
            GameApp.addTexture(textureName, "nancy-character/NancyPixel.png");
        if (!GameApp.hasTexture(slideTextureName))
            GameApp.addTexture(slideTextureName, "nancy-character/NancySlide.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Reset jumps when she touches the floor
        if (isOnGround()) {
            jumpsLeft = 2;
        }

        GameApp.startSpriteRendering();

        if (state == STATES.SLIDING)
            GameApp.drawTexture(slideTextureName, x, y - 35, 200, currentHeight);
        else
            GameApp.drawTexture(textureName, x, y - 35, 200, currentHeight);

        GameApp.endSpriteRendering();
    }

    @Override
    public void jump() {
        if (jumpsLeft <= 0) return; // No jumps left

        velocityY = jumpForce;  // Normal jump height
        jumpsLeft--;            // Consume one jump
        GameApp.playSound("jump");
    }
}
