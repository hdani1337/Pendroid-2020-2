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
import static hu.cehessteg.ballgame.BallGame.weatherCalculation;

public class MenuScreen extends MyScreen {
    public static AssetList assetList = new AssetList();
    static {
        assetList.collectAssetDescriptor(MenuStage.class,assetList);
        assetList.collectAssetDescriptor(MenuBackgroundStage.class,assetList);
    }

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        weatherBackground = new WeatherBackground(new ResponseViewport(800),game);
        addStage(weatherBackground,0,false);
        addStage(new MenuStage(game),1,true);
        addTimer(new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                weatherCalculation.step(correction*100);
                weatherBackground.setTime(weatherCalculation.getTime());
                weatherBackground.setRain(weatherCalculation.getRain());
                weatherBackground.act(correction);
                if(weatherCalculation.getM()==0){
                    preferences.putFloat("time",weatherCalculation.getTime());
                    preferences.flush();
                }
            }
        }));
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void init() {

    }

    WeatherBackground weatherBackground;
}
