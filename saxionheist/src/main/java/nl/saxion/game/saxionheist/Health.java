package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class Health {

    private int maxHealth;
    private int currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void damage(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public void reset() {
        currentHealth = maxHealth;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public void render() {
        GameApp.startSpriteRendering();

        GameApp.drawText(
                "basic",
                "Health: " + currentHealth + " / " + maxHealth,
                20,
                GameApp.getWorldHeight() - 80,
                "white"
        );

        GameApp.endSpriteRendering();
    }
}
