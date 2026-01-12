package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Player {
    /**
     * Defines the height the player will hit the floor at
     **/
    static final float floorHeight = 300f;
    /**
     * The maximum falling speed
     **/
    static final float maxFallSpeed = 400f;
    /**
     * The vertical accelaration representing gravity
     **/
    static final float gravity = 750f;
    /**
     * The vertical accelaration representing a jump
     **/
    static final float jumpForce = 400f;

    boolean isSliding = false;
    float normalHeight = 32f;
    final float slideHeight = 16f;  // half size
    float currentHeight = normalHeight;

    long slideStartTime;
    long slideDuration = 700; // milliseconds

    private String textureName;
    float x, y; // for the position
    float velocityX, velocityY;

    public Player() {
        this.x = 0f;
        this.y = 0f;
        this.textureName = "";
    }

    public Player(String textureName, float x, float y) {
        this.x = x;
        this.y = y;
        // GameApp.addTexture(textureName,"player.png");
        // -----------------------------

    }


    public void render(float delta) {
        if (GameApp.isKeyJustPressed(Input.Keys.SPACE))
            jump();

        // START SLIDE
        if (GameApp.isKeyJustPressed(Input.Keys.DOWN) && isOnGround())
            startSlide();

        // UPDATE SLIDE
        updateSlide();

        // APPLY GRAVITY
        velocityY = Math.max(velocityY - (gravity * delta), -maxFallSpeed);

        // APPLY VELOCITY
        x += velocityX * delta;
        y = Math.max(y + (velocityY * delta), floorHeight);

        // DRAW PLAYER
        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, currentHeight, "red-500");
        GameApp.endShapeRendering();
    }

    private boolean isOnGround() {
        return y <= floorHeight;
    }

    // -----------------------------
    // SLIDE LOGIC
    // -----------------------------
    private void startSlide() {
        if (isSliding) return;

        isSliding = true;
        slideStartTime = System.currentTimeMillis();

        // shrink
        currentHeight = slideHeight;
        System.out.println("slidin");

    }

    private void updateSlide() {
        if (!isSliding) return;

        long elapsed = System.currentTimeMillis() - slideStartTime;

        if (elapsed >= slideDuration) {
            stopSlide();
        }
    }

    private void stopSlide() {
        if (!isSliding) return;

        isSliding = false;

        // restore size
        currentHeight = normalHeight;
    }


    /**
     * Jump
     **/
    public void jump() {
        if (!isOnGround()) return;
        velocityY = jumpForce;
    }
    public void hitObstacle() {
        System.out.println("Player hit obstacle");
    }

}

