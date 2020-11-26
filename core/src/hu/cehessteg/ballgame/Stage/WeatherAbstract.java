package hu.cehessteg.ballgame.Stage;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public abstract class WeatherAbstract extends MyStage {
    private final static int sunset = 6 * 3600;
    private final static int sundown = 21 * 3600;
    private final static int dayNigthGradientSec = 6400;
    protected float time = 0f;
    protected float rain = 0f;



    public WeatherAbstract(Viewport viewport, MyGame game) {
        super(viewport, game);
    }

    /**
     *0 és 1 közt ad egy értéket a nap fényerejével arányosan.
     */
    public float getLight(float time){
        int daysec = (int)time % 86400;
        if (daysec < sunset){
            return 0f;
        }
        if (daysec >=sunset && daysec<=sunset + dayNigthGradientSec){
            return ((float)(daysec - sunset)) / (float)dayNigthGradientSec;
        }

        if (daysec>sunset + dayNigthGradientSec && daysec <= sundown){
            return 1f;
        }
        if (daysec> sundown && daysec <= sundown + dayNigthGradientSec){
            return 1f - ((float)(daysec - sundown)) / (float)dayNigthGradientSec;
        }
        if (daysec > sundown + dayNigthGradientSec){
            return 0f;
        }
        return 0f;
    }


    /**
     *-1 és 1 közt ad egy értéket a nap magasságáról.
     */
    public float getSunPosition(float time){
        int daysec = (int)time % 86400;
        if (daysec < sunset){
            return 1f;
        }
        if (daysec >=sunset && daysec<=sunset + dayNigthGradientSec){
            return 1f - (((float)(daysec - sunset)) / (float)dayNigthGradientSec) * 0.9f;
        }

        if (daysec>sunset + dayNigthGradientSec && daysec <= sundown){
            return 0.1f -  ((float)daysec - (float)(sunset + dayNigthGradientSec)) / (float)(sundown - (sunset + dayNigthGradientSec))*0.2f;
        }
        if (daysec> sundown && daysec <= sundown + dayNigthGradientSec){
            return - (((float)(daysec - sundown)) / (float)dayNigthGradientSec) * 0.9f - 0.1f;
        }
        if (daysec > sundown + dayNigthGradientSec){
            return -1f;
        }
        return 0f;
    }



    /**
     *-1 és 1 közt ad egy értéket a hold magasságáról.
     */
    public float getMoonPosition(float time){
        int daysec = (int)time % 86400;
        if (daysec > sunset && daysec < sundown){
            return 1f;
        }
        return 1f - (daysec < sunset ? (daysec + 86400 - sundown) : (daysec - sundown))/(float)(86400 - sundown + sunset) * 2f;
    }

    /**
     * Bemenete az idő másodpercben
     * @param time
     */
    public void setTime(float time){
        this.time = time;
    }

    /**
     * Bemenete 0 és 1 között az eső mennyisége
     * @param rain
     */
    public void setRain(float rain){
        this.rain = rain;
    }


}
