package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Player {
    static final float floorHeight = 48f;
    static final float maxFallSpeed = 400f;
    static final float gravity = 750f;
    static final float jumpForce = 450f;

    boolean isSliding = false;
    final float width = 200f;
    final float normalHeight = 200f;
    final float slideHeight = 190f;
    float currentHeight = normalHeight;

    long slideStartTime;
    long slideDuration = 800;

    protected int maxHealth = 3;

    float x, y;
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

        if (GameApp.isKeyJustPressed(Input.Keys.DOWN) && isOnGround())
            startSlide();

        updateSlide();

        velocityY = Math.max(velocityY - (gravity * delta), -maxFallSpeed);

        x += velocityX * delta;
        y = Math.max(y + (velocityY * delta), floorHeight);

        if (isOnGround()) {
            if (isSliding)
                state = STATES.SLIDING;
            else
                state = STATES.WALKING;
        } else
            state = STATES.AIR;
    }

    public void resetToStart() {
        y = floorHeight;
        isSliding = false;
        slideStartTime = 0;
        currentHeight = normalHeight;
    }

    boolean isOnGround() {
        return y <= floorHeight;
    }

    private void startSlide() {
        if (isSliding) return;

        isSliding = true;
        slideStartTime = System.currentTimeMillis();
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
        currentHeight = normalHeight;
    }

    public void jump() {
        if (!isOnGround()) return;

        velocityY = jumpForce;
        GameApp.playSound("jump");
    }


    public void hitObstacle() {
        System.out.println("Player hit obstacle");
    }
}


