package hu.cehessteg.ballgame.Screen;

import hu.cehessteg.ballgame.BallGame;
import hu.cehessteg.ballgame.Stage.IntroStage;
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
import static hu.cehessteg.ballgame.BallGame.weatherCalculation;

public class IntroScreen extends MyScreen {
    public static AssetList assetList = new AssetList();
    static {
        assetList.collectAssetDescriptor(IntroStage.class,assetList);
        assetList.collectAssetDescriptor(WeatherBackground.class, assetList);
    }

    public IntroScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        ((BallGame)game).createWeather();
        addStage(weatherBackground,0,false);
        addTimer(weatherAct);
        addStage(new IntroStage(game),1,false);
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void init() {

    }
}
