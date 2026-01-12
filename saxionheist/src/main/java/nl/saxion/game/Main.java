package nl.saxion.game;

import nl.saxion.game.saxionheist.CharacterSelectScreen;
import nl.saxion.game.saxionheist.YourGameScreen;
import nl.saxion.game.saxionheist.MainMenuScreen;
import nl.saxion.gameapp.GameApp;

public class Main {
    public static void main(String[] args) {
        // Add screens
        GameApp.addScreen("MainMenuScreen", new MainMenuScreen());
        GameApp.addScreen("CharacterSelectScreen", new CharacterSelectScreen());
        GameApp.addScreen("YourGameScreen", new YourGameScreen());

        // Start game loop and show main menu screen
        GameApp.start("SaxionHeist", 800, 450, 60, false, "MainMenuScreen");
    }
}
