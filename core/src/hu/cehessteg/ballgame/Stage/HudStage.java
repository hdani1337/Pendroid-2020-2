package hu.cehessteg.ballgame.Stage;

import hu.cehessteg.ballgame.Hud.Pause;
import hu.cehessteg.ballgame.Hud.ScoreBoard;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettyStage;

public class HudStage extends PrettyStage {
    public static AssetList assetList = new AssetList();
    static {
        //add assets here
    }

    //public static CardStage stage;//Hátha kell a GameStageből valami
    private Pause pause;
    private ScoreBoard scoreBoard;

    public HudStage(MyGame game) {
        super(game);
    }

    @Override
    public void assignment() {
        pause = new Pause(game);
        scoreBoard = new ScoreBoard(game);
    }

    @Override
    public void setSizes() {
        pause.setSize(140,140);
        scoreBoard.setSizes(getViewport());
    }

    @Override
    public void setPositions() {
        pause.setPosition(15,getViewport().getWorldHeight()-pause.getHeight()-15);
        scoreBoard.setPosition(getViewport().getWorldWidth()-scoreBoard.getWidth(),0);
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void setZIndexes() {

    }

    @Override
    public void addActors() {
        addActor(scoreBoard);
        addActor(pause);
    }
}
