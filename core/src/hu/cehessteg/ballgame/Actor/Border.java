package hu.cehessteg.ballgame.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;

public class Border extends OneSpriteStaticActor {
    public BorderType type;

    public Border(MyGame game, World world, BorderType borderType, MyStage stage) {
        super(game, "pic/fekete.png");
        this.type = borderType;
        setActorWorldHelper(new Box2DWorldHelper(world,this, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.StaticBody));
        switch (borderType){
            case ALSO:
                setWidth(stage.getWidth()*2);
                setHeight(1);
                setOrigin(0,0);
                setPosition(0,0);
                break;
            case FELSO:
                setWidth(stage.getWidth()*2);
                setHeight(1);
                setOrigin(0,0);
                setPosition(0,stage.getHeight()+0.5f);
                break;
            case BAL:
                setWidth(1);
                setHeight(stage.getHeight()*2);
                setOrigin(0,0);
                setPosition(-0.5f,0);
                break;
            case JOBB:
                setWidth(1);
                setHeight(stage.getHeight()*2);
                setOrigin(0,0);
                setPosition(stage.getWidth()+0.5f,0);
                break;
        }
    }
}
