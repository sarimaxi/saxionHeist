package nl.saxion.game.saxionheist;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;

public class Obstacle {
    private String textureName;
    private float x;
    private float y;

    public Obstacle(String textureName, float x, float y) {
        this.textureName = textureName;
        this.x = x;
        this.y = y;
    }

    public void render(float delta) {
        //GameApp.drawTexture(textureName, (int) x, (int) y);
        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32, Color.RED);
        GameApp.endShapeRendering();

    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }
}
