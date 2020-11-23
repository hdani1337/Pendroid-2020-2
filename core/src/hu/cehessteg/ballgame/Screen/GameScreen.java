package hu.cehessteg.ballgame.Screen;

import hu.cehessteg.ballgame.Stage.GameOverStage;
import hu.cehessteg.ballgame.Stage.HudStage;
import hu.cehessteg.ballgame.Stage.PauseStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class GameScreen extends MyScreen {
    public static AssetList assetList = new AssetList();
    static {
        //assetList.collectAssetDescriptor(CardStage.class, assetList);
        assetList.collectAssetDescriptor(HudStage.class, assetList);
        assetList.collectAssetDescriptor(GameOverStage.class, assetList);
        assetList.collectAssetDescriptor(PauseStage.class, assetList);
    }

    public GameScreen(MyGame game) {
        super(game);
    }

    //public CardStage cardStage;

    @Override
    protected void afterAssetsLoaded() {
        /*addStage(new TableStage(game),0,false);
        cardStage = new CardStage(game);
        HudStage.stage = cardStage;
        addStage(cardStage,1,true)*/
        addStage(new HudStage(game),2, true);
        addStage(new PauseStage(game),3, true);
        addStage(new GameOverStage(game),4, true);
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void init() {

    }
}
