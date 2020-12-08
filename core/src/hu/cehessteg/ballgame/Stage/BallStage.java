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

        int ballCount = OptionsStage.ballCount;
        if (ballCount == 0) ballCount = 1;

        switch (ballCount){
            case 1: {
                Ball ball = new Ball(game, world, BallType.values()[index]);
                ball.setPosition(getViewport().getWorldWidth() / 2 - ball.getWidth() / 2, 1);
                addActor(ball);
                break;
            }
            case 2: {
                Ball ball1 = new Ball(game, world, BallType.values()[index]);
                Ball ball2 = new Ball(game, world, BallType.values()[index]);
                ball1.setPosition(getViewport().getWorldWidth() / 2 - ball1.getWidth() - 0.25f, 1);
                ball2.setPosition(getViewport().getWorldWidth() / 2 + 0.25f, 1);
                addActor(ball1);
                addActor(ball2);
                break;
            }
            case 3: {
                Ball ball1 = new Ball(game, world, BallType.values()[index]);
                Ball ball2 = new Ball(game, world, BallType.values()[index]);
                Ball ball3 = new Ball(game, world, BallType.values()[index]);
                ball1.setPosition(0.6f, 1);
                ball2.setPosition(3.35f,1);
                ball3.setPosition(6.1f,1);
                addActor(ball1);
                addActor(ball2);
                addActor(ball3);
                break;
            }
        }
    }

    @Override
    public void act(float delta) {
        if(isAct)
            super.act(delta);
        else if(isGameOver)
            super.act(delta);
    }
}
