package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class Player {
    private String textureName;
    float x, y; // for the position


    public Player (String textureName, float x, float y){
        this.x = x;
        this.y = y;
        // GameApp.addTexture(textureName,"player.png");
    }
    public void render ( float delta){
        GameApp.startShapeRenderingFilled();
        GameApp.drawRect(x, y, 32, 32, "red-500");
        GameApp.endShapeRendering();
     //   GameApp.drawTexture(textureName,x,y);

    }
    public void whatever(String cheese){
        System.out.println("hello");
    }
}

