package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class NancyCharacter extends Player {

    private final String textureName = "nancy_run";
    private final String slideTextureName = "nancy_slide";

    private int jumpsLeft = 2; // Nancy can jump twice before landing

    public float redness = 0f;
    public float blink = 0f;

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
            draw(slideTextureName, 1f, 1f - 0.8f * redness, 1f - 0.8f * redness, 1f - blink);
        else
            draw(textureName, 1f, 1f - 0.8f * redness, 1f - 0.8f * redness, 1f - blink);

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
