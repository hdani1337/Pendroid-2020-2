package hu.cehessteg.ballgame.Stage;

import hu.cehessteg.ballgame.Actor.Ball;
import hu.cehessteg.ballgame.Actor.BallType;
import hu.cehessteg.ballgame.Actor.Border;
import hu.cehessteg.ballgame.Actor.BorderType;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettySimpleStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;

public class BallStage extends Box2dStage {
    public static boolean isGameOver;
    public static boolean isAct;
    public static long score;

    public BallStage(MyGame game) {
        super(new ResponseViewport(9),game);
        isGameOver = false;
        isAct = true;
        score = 0;
        addActor(new Border(game,world, BorderType.ALSO,this));
        int index = OptionsStage.ballType-1;
        if (index == -1) index = 0;
        Ball ball = new Ball(game,world, BallType.values()[index]);
        ball.setPosition(getViewport().getWorldWidth()/2-ball.getWidth()/2,1);
        addActor(ball);
    }

    @Override
    public void act(float delta) {
        if(isAct)
            super.act(delta);
        else if(isGameOver)
            super.act(delta);
    }
}
