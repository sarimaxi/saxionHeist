package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class AlexCharacter extends Player {

    public AlexCharacter() {
        super();
        maxHealth = 5;

        if (!GameApp.hasTexture("alex_runjump"))
            GameApp.addTexture("alex_runjump", "alex-character/AlexandraPixel.png");

        if (!GameApp.hasTexture("alex_slide"))
            GameApp.addTexture("alex_slide", "alex-character/AlexandraSlide.png");

        slideTextureName = "alex_slide";
    }

    public AlexCharacter(float x, float y) {
        super("alex_runjump", x, y);
        maxHealth = 5;

        if (!GameApp.hasTexture("alex_runjump"))
            GameApp.addTexture("alex_runjump", "alex-character/AlexandraPixel.png");

        if (!GameApp.hasTexture("alex_slide"))
            GameApp.addTexture("alex_slide", "alex-character/AlexandraSlide.png");

        slideTextureName = "alex_slide";
    }
}