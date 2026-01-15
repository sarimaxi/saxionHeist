package nl.saxion.game;

import nl.saxion.game.saxionheist.CharacterSelectScreen;
import nl.saxion.game.saxionheist.YourGameScreen;
import nl.saxion.gameapp.GameApp;

public class Main {
    public static void main(String[] args) {
        GameApp.addScreen("CharacterSelectScreen", new CharacterSelectScreen());
        GameApp.addScreen("YourGameScreen", new YourGameScreen());

        GameApp.start("SaxionHeist", 800, 450, 60, false, "CharacterSelectScreen");
    }
}
