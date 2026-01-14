package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class PujaCharacter extends Player {

    public PujaCharacter() {
        super("puja_runjump", 0, 0);

        maxHealth = 5;

        if (!GameApp.hasTexture("puja_runjump"))
            GameApp.addTexture("puja_runjump", "puja-character/puja-run.png");

        if (!GameApp.hasTexture("puja_slide"))
            GameApp.addTexture("puja_slide", "puja-character/puja-bend.png");

        slideTextureName = "puja_slide";
    }
}

