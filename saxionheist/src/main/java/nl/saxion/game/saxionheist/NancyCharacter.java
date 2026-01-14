package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

public class NancyCharacter extends Player {

    public NancyCharacter() {
        // Use a TEXTURE KEY (not a file path)
        super("nancy_runjump", 0, 0);

        maxHealth = 5;

        if (!GameApp.hasTexture("nancy_runjump")) {
            GameApp.addTexture(
                    "nancy_runjump",
                    "nancy-character/nancy-run.png"
            );
        }


        if (!GameApp.hasTexture("nancy_slide")) {
            GameApp.addTexture(
                    "nancy_slide",
                    "nancy-character/nancy-bend.png"
            );
        }


        slideTextureName = "nancy_slide";
    }
}
