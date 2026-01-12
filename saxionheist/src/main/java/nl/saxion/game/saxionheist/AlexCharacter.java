package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class AlexCharacter extends Player {

    public AlexCharacter() {
        super("alex_runjump", 0, 0);

        maxHealth = 5;

        if (!GameApp.hasTexture("alex_runjump"))
            GameApp.addTexture("alex_runjump", "alex-character/AlexandraPixel.png");

        if (!GameApp.hasTexture("alex_slide"))
            GameApp.addTexture("alex_slide", "alex-character/AlexandraSlide.png");

        slideTextureName = "alex_slide";
    }
}
