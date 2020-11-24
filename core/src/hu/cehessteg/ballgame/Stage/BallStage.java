package hu.cehessteg.ballgame.Stage;

import hu.cehessteg.ballgame.Actor.Ball;
import hu.cehessteg.ballgame.Actor.Border;
import hu.cehessteg.ballgame.Actor.BorderType;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettySimpleStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;

public class BallStage extends Box2dStage {
    public static boolean gameOver;

    public BallStage(MyGame game) {
        super(new ResponseViewport(9),game);
        gameOver = false;
        addActor(new Border(game,world, BorderType.ALSO,this));
        /*addActor(new Border(game,world, BorderType.BAL,this));
        addActor(new Border(game,world, BorderType.JOBB,this));
        addActor(new Border(game,world, BorderType.FELSO,this));*/

        Ball ball = new Ball(game,world);
        ball.setPosition(getViewport().getWorldWidth()/2-ball.getWidth()/2,1);
        addActor(ball);
    }
}
