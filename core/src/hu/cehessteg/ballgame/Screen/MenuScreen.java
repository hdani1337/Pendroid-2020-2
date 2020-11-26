package hu.cehessteg.ballgame.Screen;

import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.cehessteg.ballgame.Stage.MenuBackgroundStage;
import hu.cehessteg.ballgame.Stage.MenuStage;
import hu.cehessteg.ballgame.Stage.WeatherBackground;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

import static hu.cehessteg.ballgame.BallGame.preferences;
import static hu.cehessteg.ballgame.BallGame.weatherAct;
import static hu.cehessteg.ballgame.BallGame.weatherBackground;


public class MenuScreen extends MyScreen {
    public static AssetList assetList = new AssetList();
    static {
        assetList.collectAssetDescriptor(MenuStage.class,assetList);
        assetList.collectAssetDescriptor(MenuBackgroundStage.class,assetList);
        assetList.collectAssetDescriptor(WeatherBackground.class, assetList);
    }

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(weatherBackground,0,false);
        addTimer(weatherAct);
        addStage(new MenuStage(game),1,true);
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void init() {

    }
}
