package nl.saxion.game.saxionheist;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class YourGameScreen extends ScalableGameScreen {
    Player player = null;


    public YourGameScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        player = new Player("String",300,400);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        player.render(delta);

    }

    private String getRandomColor() {
        int randomIndex = (int)GameApp.random(0, GameApp.getAllColors().length-1);
        return GameApp.getAllColors()[randomIndex];
    }

    @Override
    public void hide() {

    }
}
