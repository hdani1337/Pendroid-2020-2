package hu.cehessteg.ballgame.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.ballgame.Stage.BallStage;
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

import static hu.cehessteg.ballgame.Stage.BallStage.isAct;
import static hu.cehessteg.ballgame.Stage.BallStage.isGameOver;
import static hu.cehessteg.ballgame.Stage.BallStage.score;

public class Ball extends OneSpriteStaticActor {
    public boolean started;

    public Ball(MyGame game, World world) {
        super(game, "pic/gombok/info_i.png");
        this.started = false;
        setActorWorldHelper(new Box2DWorldHelper(world,this, ShapeType.Circle, new MyFixtureDef(), BodyDef.BodyType.DynamicBody));
        setSize(2,2);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!isGameOver && isAct) {
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
}