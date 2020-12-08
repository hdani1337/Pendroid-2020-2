package hu.cehessteg.ballgame.Stage;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.cehessteg.ballgame.Hud.TextBox;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class WeatherBackground extends WeatherAbstract {
    private static Random random = new Random();

    private static final String SKYCLEAR_TEXTURE = "weather/skyclear.png";
    private static final String SKYCLOUDY_TEXTURE ="weather/skycloudy.png";
    private static final String CLOUD4_TEXTURE = "weather/cloud4.png";
    private static final String CLOUD3_TEXTURE = "weather/cloud3.png";
    private static final String CLOUD2_TEXTURE = "weather/cloud2.png";
    private static final String CLOUD1_TEXTURE = "weather/cloud1.png";
    private static final String SUNDOWN_TEXTURE = "weather/sundown.png";
    private static final String SUNDAYLIGHT_TEXTURE = "weather/sundaylight.png";
    public static final String PLAYFIELD_TEXTURE = "hatter.png";

    //region AssetList
    public static final String BLACK_TEXTURE = "pic/fekete.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(SKYCLEAR_TEXTURE);
        assetList.addTexture(SKYCLOUDY_TEXTURE);
        assetList.addTexture(CLOUD4_TEXTURE);
        assetList.addTexture(CLOUD3_TEXTURE);
        assetList.addTexture(CLOUD2_TEXTURE);
        assetList.addTexture(CLOUD1_TEXTURE);
        assetList.addTexture(SUNDOWN_TEXTURE);
        assetList.addTexture(SUNDAYLIGHT_TEXTURE);
        assetList.addTexture(PLAYFIELD_TEXTURE);
    }
    //endregion

    private class SkyActor extends MultiSpriteActor{
        OffsetSprite clear;
        OffsetSprite cloudy;

        public SkyActor(MyGame game, float width, float height) {
            super(game, width, height);
        }

        @Override
        public void init() {
            super.init();
            addSprite(clear = new  OffsetSprite(game.getMyAssetManager().getTexture(SKYCLEAR_TEXTURE),0,0,getWidth(),getHeight()));
            addSprite(cloudy = new  OffsetSprite(game.getMyAssetManager().getTexture(SKYCLOUDY_TEXTURE),0,0,getWidth(),getHeight()));
            setRain(0);
        }

        public void setRain(float rain){
            cloudy.setAlpha(rain * 3 > 1 ? 1 : rain * 3);
        }

    };


    private class SunActor extends MultiSpriteActor{
        OffsetSprite sun;
        OffsetSprite sundown;
        float alpha = 1f;
        float sundownF = 0f;

        public SunActor(MyGame game, float width, float height) {
            super(game, width, height);
        }

        @Override
        public void init() {
            super.init();
            setDebug(false);
            addSprite(sun = new  OffsetSprite(game.getMyAssetManager().getTexture(SUNDAYLIGHT_TEXTURE),0,0,getWidth(),getHeight()));
            addSprite(sundown = new  OffsetSprite(game.getMyAssetManager().getTexture(SUNDOWN_TEXTURE),0,0,getWidth(),getHeight()));
            setSundown(0);
        }

        public void setSundown(float sundown){
           this.sundownF = sundown;
           setSun();
        }

        public void setAlpha(float alpha){
            this.alpha = alpha;
            setSun();
        }

        private void setSun(){
            this.sundown.setAlpha(sundownF * alpha);
            this.sun.setAlpha((1f - sundownF) * alpha);
        }
    };


    private class Cloud extends OneSpriteStaticActor{
        float speed;
        float alpha = 0;
        float ontime;

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

        boolean delete = false;
        public Cloud(MyGame game) {
            super(game,CLOUD1_TEXTURE);
            setDebug(false);
            switch (random.nextInt(4)){
                case 0:
                    sprite.setTexture(game.getMyAssetManager().getTexture(CLOUD2_TEXTURE));
                    break;
                case 1:
                    sprite.setTexture(game.getMyAssetManager().getTexture(CLOUD3_TEXTURE));
                    break;
                case 2:
                    sprite.setTexture(game.getMyAssetManager().getTexture(CLOUD4_TEXTURE));
                    break;
            }
            sprite.setAlpha(0f);
            ontime = random.nextInt(10) + 2;
            speed = random.nextFloat() * 4f + 1f;
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            alpha+=delta / (float)ontime;
            sprite.setAlpha(alpha < 0.7f ? alpha : 0.7f);
            setX(getX() + speed * delta);
            if (!isInFrustum(1.2f)){
                if (delete){
                    getStage().getActors().removeValue(this,true);
                }else {
                    setX(-getWidth());
                }
            }
        }
    }

    private SkyActor skyActor;
    private SunActor sunActor;
    private OneSpriteStaticActor playfieldActor;

    public WeatherBackground(Viewport viewport, MyGame game) {
        super(viewport, game);
        this.viewport = viewport;
        skyActor = new SkyActor(game,viewport.getWorldWidth(), viewport.getWorldHeight());
        sunActor = new SunActor(game,getWidth() / 2, (getWidth() / 16 * 9) / 2);
        sunActor.setX(viewport.getWorldWidth()*0.01f);
        playfieldActor = new OneSpriteStaticActor(game,PLAYFIELD_TEXTURE);
        playfieldActor.setSize(viewport.getWorldWidth(),(viewport.getWorldWidth()/playfieldActor.getWidth())*playfieldActor.getHeight());

        addActor(skyActor);
        addActor(sunActor);
        addActor(playfieldActor);
        skyActor.setDebug(false);
        sunActor.setDebug(false);
        playfieldActor.setZIndex(1000);
    }

    private Viewport viewport;

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime+=delta;
        float light = getLight(time);
        //skyActor.setY(-(skyActor.getHeight() - getHeight()) + light * skyActor.getHeight() * 0.8f);
        skyActor.setRain(rain);

        sunActor.setY(getHeight() * 0.92f - sunActor.getHeight() / 2 + getHeight() * getSunPosition(time) / 1.75f);
        sunActor.setSundown(getSunPosition(time) > 0 ? 0 : (1f - light * light> 0f ? 1f - light * light : 0f));
        sunActor.setAlpha(1- rain * 2 < 0 ? 0 : 1- rain * 2);

        int cofc = 0;
        float rainfactor = random.nextFloat()*50;

        for (Actor a: getActors()) {
            if (a instanceof Cloud){
                cofc++;
                if (cofc > (int)rainfactor){
                    ((Cloud) a).setDelete(true);
                }
            }

        }
        if (cofc < rainfactor) {
            Cloud c;
            addActor(c = new Cloud(game));
            c.setPosition(random.nextInt((int)getWidth() * 2) - (int)getWidth(), getHeight() *0.85f - random.nextInt((int)(getHeight() / 2f)));
            c.setWidth(c.getWidth() / 2);
            c.setHeight(c.getHeight() / 2);
            c.setZIndex(500);
            //c.setY(-(c.getHeight() - getHeight()));

        }

    }

    @Override
    public void dispose() {
        //just do nothing...¯\_(ツ)_/¯
    }
}
