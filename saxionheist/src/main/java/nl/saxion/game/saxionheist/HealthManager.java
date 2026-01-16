package nl.saxion.game.saxionheist;

import com.badlogic.gdx.math.Interpolation;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.animation.Tween;

import java.lang.reflect.Field;

public class HealthManager {
    private final int maxHealth;
    private int currentHealth;
    private float cooldown = 0;

    private Player player;
    private Tween blinkTween, hitTween;

    static final String FONTNAME = "health-ui";

    public HealthManager(Player player) {
        this.maxHealth = player.maxHealth;
        this.currentHealth = maxHealth;
        this.player = player;

        if(!GameApp.hasSound("hit"))
            GameApp.addSound("hit", "sounds/hit.wav");

        if (!GameApp.hasFont(FONTNAME))
            GameApp.addFont(FONTNAME, "fonts/basic.ttf", 64);
    }

    public void render(float delta) {
        // Cooldown and blinking during cooldown
        if (cooldown > 0) {
            cooldown -= delta;

            if (blinkTween != null) {
                if (blinkTween.isFinished())
                    blinkTween.reset(true);

                blinkTween.update(delta);
            }
        }
        else if (blinkTween != null) {
            setField(player,"blink", 0f);
            blinkTween = null;
        }

        // Update hit flash
        if (hitTween != null) {
            if (hitTween.isFinished())
                hitTween = null;
            else
                hitTween.update(delta);
        }

        // Draw text
        GameApp.startSpriteRendering();
        GameApp.drawText(FONTNAME, "Health: " + currentHealth + " / " + maxHealth, 0, GameApp.getWorldHeight() - 80, "black");
        GameApp.endSpriteRendering();
    }

    public void damage(int amount) {
        damage(amount, 1f);
    }
    public void damage(int amount, float cooldown) {
        if (this.cooldown > 0) return;

        // Setup Tweens
        if (blinkTween != null) {
            blinkTween = null;
            setField(player,"blink", 0f);
        }

        if (hitTween != null)
            hitTween = null;

        setField(player, "redness", 1f);
        hitTween = Tween.on(player).to("redness",0f).duration(0.5f).ease(Interpolation.pow2Out);
        blinkTween = Tween.on(player).to("blink",0.75f).delay(0.2f).duration(0.1f).ease(Interpolation.linear);

        // Update Health
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

    private void setField(Object obj, String property, float value) {
        try {
            Field field = obj.getClass().getDeclaredField(property);
            field.setAccessible(true);
            field.setFloat(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field '" + property + "' on object: " + e.getMessage());
        }
    }
}
