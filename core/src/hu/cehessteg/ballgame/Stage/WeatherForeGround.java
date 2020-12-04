package hu.cehessteg.ballgame.Stage;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.cehessteg.ballgame.Stage.WeatherAbstract;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;

public class WeatherForeGround extends WeatherAbstract {
    private static final String NIGHT_TEXTURE = "weather/night.png";

    //region AssetList
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(NIGHT_TEXTURE);
    }
    //endregion

    private OneSpriteStaticActor night;

    private final static float nightAlpha = 0.5f;

    public WeatherForeGround(MyGame game) {
        super(new ResponseViewport(800), game);
        addActor(night = new OneSpriteStaticActor(game,NIGHT_TEXTURE));
        night.setSize(getWidth(), getHeight());
        night.setDebug(false);
        night.setTouchable(null);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
        float light = getLight(time);
        night.setAlpha(nightAlpha * (1f - light));
    }

    @Override
    public void dispose() {
        //just do nothing...¯\_(ツ)_/¯
    }
}
