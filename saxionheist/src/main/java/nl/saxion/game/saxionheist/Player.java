package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Player {
    /**
     * Defines the height the player will hit the floor at
     **/
    static final float floorHeight = 105f;
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
    static final float jumpForce = 450f;

    boolean isSliding = false;
    final float normalHeight = 200f;
    final float slideHeight = 190f;  // half size
    float currentHeight = normalHeight;

    long slideStartTime;
    long slideDuration = 800; // milliseconds

    protected int maxHealth = 3;

    float x, y; // for the position
    float velocityX, velocityY;
    
    STATES state = STATES.AIR;
    enum STATES {
        WALKING,
        SLIDING,
        AIR
    }

    public Player() {
        this.x = 0f;
        this.y = 0f;
      
        if(!GameApp.hasSound("jump"))
            GameApp.addSound("jump", "sounds/jump.wav");
    }

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
      
        if(!GameApp.hasSound("jump"))
              GameApp.addSound("jump", "sounds/jump.wav");
    }

    public int getMaxHealth() {
        return maxHealth;
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
        if (isOnGround()) {
            if(isSliding)
                state =  STATES.SLIDING;
            else
                state = STATES.WALKING;
        }
        else
            state = STATES.AIR;
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
        GameApp.playSound("jump");
    }

    public void hitObstacle() {
        System.out.println("Player hit obstacle");
    }
}

