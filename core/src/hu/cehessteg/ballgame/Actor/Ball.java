package hu.cehessteg.ballgame.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.ballgame.Stage.BallStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyContactListener;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.WorldActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

import static hu.cehessteg.ballgame.BallGame.muted;
import static hu.cehessteg.ballgame.Stage.BallStage.isAct;
import static hu.cehessteg.ballgame.Stage.BallStage.isGameOver;
import static hu.cehessteg.ballgame.Stage.BallStage.score;

public class Ball extends OneSpriteStaticActor {
    public boolean started;
    protected BallType ballType;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addSound("sound/soccer.mp3");
        assetList.addSound("sound/volleyball.mp3");
        assetList.addSound("sound/basketball.mp3");
        assetList.addSound("sound/basketball.mp3");
    }

    public Ball(MyGame game, World world, BallType ballType) {
        super(game, getHash(ballType));
        this.started = false;
        this.ballType = ballType;
        setActorWorldHelper(new Box2DWorldHelper(world,this, ShapeType.Circle, getFixtureDef(), BodyDef.BodyType.DynamicBody));
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!isGameOver && isAct) {
                    playSound();
                    if (started == false) started = true;
                    ((Box2DWorldHelper) getActorWorldHelper()).getBody().applyForceToCenter((getWidth() * 0.4f - x) * 3000, (getHeight() * 0.75f - y) * 8000, true);
                    setOrigintoCenter();
                    score++;
                    //((SimpleWorldHelper)getActorWorldHelper()).getBody().rotateTo(5,(getWidth()*0.4f-x*(getHeight()*0.45f-y)*5)*90);
                }
            }
        });
        ((Box2DWorldHelper)getActorWorldHelper()).addContactListener(new MyContactListener(){
            @Override
            public void beginContact(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {
                if(otherHelper.actor instanceof Border){
                    if(((Border) otherHelper.actor).type == BorderType.ALSO){
                        if(started){
                            isGameOver = true;
                            isAct = false;
                            if(getX()>-getWidth() && getX()<getStage().getViewport().getWorldWidth()+getWidth()) playSound();
                        }
                    }
                }
            }

            @Override
            public void endContact(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {

            }

            @Override
            public void preSolve(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {

            }

            @Override
            public void postSolve(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {

            }
        });

        addTimer(new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                if(getX()<-getWidth() || getX()>getStage().getWidth()+getWidth()) {
                    isGameOver = true;
                    isAct = false;
                }
            }
        }));
    }

    public void playSound(){
        if(!muted){
            switch (ballType) {
                case SOCCER:
                    game.getMyAssetManager().getSound("sound/soccer.mp3").play();
                    break;
                case VOLLEY:
                    game.getMyAssetManager().getSound("sound/volleyball.mp3").play();
                    break;
                case BASKET:
                    game.getMyAssetManager().getSound("sound/basketball.mp3").play();
                    break;
                case BASEBALL:
                    game.getMyAssetManager().getSound("sound/baseball.mp3").play();
                    break;
            }
        }
    }

    protected static String getHash(BallType ballType){
        switch (ballType) {
            case SOCCER: default:
                return "fociLabda.png";
            case VOLLEY:
                return "ropLabda.png";
            case BASKET:
                return "kosarLabda.png";
            case BASEBALL:
                return "BaseballLabda.png";
        }
    }

    protected MyFixtureDef getFixtureDef(){
        switch (ballType) {
            case SOCCER:
                setSize(2.5f,2.5f);
                return new MyFixtureDef(5);
            case VOLLEY:
                setSize(2.5f,2.5f);
                return new MyFixtureDef(4);
            case BASKET:
                setSize(2.5f,2.5f);
                return new MyFixtureDef(4.5f);
            case BASEBALL:
                setSize(1,1);
                return new MyFixtureDef(7);
            default:
                return new MyFixtureDef();
        }
    }
}
