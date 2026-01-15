package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class HealthManager {

    private int currentHealth;
    private final int maxHealth;
    private float cooldown = 0;

    static final String FONTNAME = "health-ui";

    public HealthManager(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;

        if(!GameApp.hasSound("hit"))
            GameApp.addSound("hit", "sounds/hit.wav");

        if (!GameApp.hasFont(FONTNAME))
            GameApp.addFont(FONTNAME, "fonts/basic.ttf", 64);
    }

    public void render(float delta) {
        if (cooldown > 0)
            cooldown -= delta;

        GameApp.startSpriteRendering();
        GameApp.drawText(FONTNAME, "Health: " + currentHealth + " / " + maxHealth, 0, GameApp.getWorldHeight() - 80, "black");
        GameApp.endSpriteRendering();
    }

    public void damage(int amount) {
        damage(amount, 1f);
    }
    public void damage(int amount, float cooldown) {
        if (this.cooldown > 0) return;

        currentHealth = Math.clamp(currentHealth - amount, 0, Integer.MAX_VALUE);
        this.cooldown += cooldown;

        GameApp.playSound("hit");
    }

    public void reset() {
        currentHealth = maxHealth;
        cooldown = 0;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }
}
