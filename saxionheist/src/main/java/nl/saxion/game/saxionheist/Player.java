package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Player {
    /** Defines the height the player will hit the floor at **/
    static final float floorHeight = 300f;
    /** The maximum falling speed **/
    static final float maxFallSpeed = 400f;
    /** The vertical accelaration representing gravity **/
    static final float gravity = 750f;
    /** The vertical accelaration representing a jump **/
    static final float jumpForce = 400f;

    boolean isSliding = false;
    float normalHeight = 32f;
    float slideHeight = 16f;  // half size
    float currentHeight = normalHeight;

    long slideStartTime;
    long slideDuration = 350; // milliseconds

    private String textureName;
    float x, y; // for the position
    float velocityX, velocityY;

    public Player (String textureName, float x, float y){
        this.x = x;
        this.y = y;
        // GameApp.addTexture(textureName,"player.png");
    }

    public void render (float delta) {
        if (GameApp.isKeyJustPressed(Input.Keys.SPACE))
            jump();

        velocityY = Math.max(velocityY - (gravity * delta), -maxFallSpeed);

        // Apply velocities
        x += velocityX * delta;
        y = Math.max(y + (velocityY * delta), floorHeight);

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32, "red-500");
        GameApp.endShapeRendering();
        //   GameApp.drawTexture(textureName,x,y);

        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32, "red-500");
        GameApp.endShapeRendering();
        //  GameApp.drawTexture(textureName,x,y);

        // START SLIDE
        if (GameApp.isKeyJustPressed(Input.Keys.DOWN))
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

    // -----------------------------
    // SLIDE LOGIC
    // -----------------------------
    private void startSlide() {
        if (isSliding) return;
        if (velocityY != 0) return;  // cannot slide in air

        isSliding = true;
        slideStartTime = System.currentTimeMillis();

        // shrink
        currentHeight = slideHeight;

        // move down so feet stay on the ground
        y -= (normalHeight - slideHeight);
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
        y += (normalHeight - slideHeight);
        currentHeight = normalHeight;
    }


    /** Jump **/
    public void jump () {
        velocityY = jumpForce;
    }

}

