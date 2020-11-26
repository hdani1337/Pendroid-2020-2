package hu.cehessteg.ballgame.Screen;

import hu.cehessteg.ballgame.Stage.MenuBackgroundStage;
import hu.cehessteg.ballgame.Stage.MenuStage;
import hu.cehessteg.ballgame.Stage.WeatherBackground;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;

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
        addStage(new WeatherBackground(new ResponseViewport(9),game),0,false);
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
