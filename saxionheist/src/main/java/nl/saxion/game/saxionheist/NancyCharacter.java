package nl.saxion.game.saxionheist;

import nl.saxion.gameapp.GameApp;

    public class NancyCharacter extends Player {

        public NancyCharacter() {
            super("nancy_runjump", 0, 0);

            maxHealth = 5;

            if (!GameApp.hasTexture("nancy_runjump"))
                GameApp.addTexture("nancy_runjump", "/nancy-character/NancyRun.png");

            if (!GameApp.hasTexture("nancy_slide"))
                GameApp.addTexture("nancy_slide", "/nancy-character/NancySlide.png");

            slideTextureName = "nancy_slide";
        }
    }


