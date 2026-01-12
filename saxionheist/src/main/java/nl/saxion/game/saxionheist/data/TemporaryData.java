package nl.saxion.game.saxionheist.data;

import nl.saxion.game.saxionheist.Player;
import nl.saxion.gameapp.GameApp;

public class TemporaryData {
    public Player character;

    public static Player getCharacter() {
        return getStored().character;
    }

    public static void setCharacter(Player character) {
        TemporaryData data = getStored();
        data.character = character;
        store(data);
    }

    private static void store(TemporaryData data) {
        GameApp.addItemToStore("temp",data);
    }

    private static TemporaryData getStored() {
        if (!GameApp.hasItemInStore("temp"))
            return new TemporaryData();

        return (TemporaryData)GameApp.getItemFromStore("temp");
    }
}
