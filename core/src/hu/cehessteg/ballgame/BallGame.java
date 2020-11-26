package hu.cehessteg.ballgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import hu.cehessteg.ballgame.Screen.GameScreen;
import hu.cehessteg.ballgame.Screen.IntroScreen;
import hu.cehessteg.ballgame.Screen.MenuScreen;
import hu.cehessteg.ballgame.Screen.OptionsScreen;
import hu.cehessteg.ballgame.Stage.LoadingStage;
import hu.cehessteg.ballgame.Stage.WeatherBackground;
import hu.cehessteg.ballgame.Stage.WeatherCalculation;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class BallGame extends MyGame {
	public static WeatherCalculation weatherCalculation;

	public BallGame(){
	}

	public BallGame(boolean debug){
		super(debug);
	}

	public static Preferences preferences;//Mentés
	public static boolean muted;//Némítva van e a játék

	@Override
	public void create() {
		super.create();
		setLoadingStage(new LoadingStage(this));
		setScreen(new IntroScreen(this));
		weatherCalculation = new WeatherCalculation(0);
		try {
			preferences = Gdx.app.getPreferences("frameworkSave");
			muted = preferences.getBoolean("muted");
			Gdx.app.getGraphics().setTitle("Remember Me");
			Gdx.app.getGraphics().setResizable(false);
			weatherCalculation.setTime(preferences.getFloat("time"));
			//setDisplay();
		}catch (NullPointerException e){
			/**Ha NullPointert kapunk, akkor még nincsenek mentett adatok**/
		}
	}


	public static WeatherBackground weatherBackground;
	public static PermanentTimer weatherAct;

	public void createWeather(){
		weatherBackground = new WeatherBackground(new ResponseViewport(800),this);
		weatherAct = new PermanentTimer(new PermanentTimerListener(){
			@Override
			public void onTick(PermanentTimer sender, float correction) {
				super.onTick(sender, correction);
				weatherCalculation.step(correction*800);
				weatherBackground.setTime(weatherCalculation.getTime());
				weatherBackground.setRain(weatherCalculation.getRain());
				weatherBackground.act(correction);
				if(weatherCalculation.getM()==0){
					preferences.putFloat("time",weatherCalculation.getTime());
					preferences.flush();
				}
				System.out.println(weatherCalculation.getTimeToString());
			}
		});
	}

	private static void setDisplay(){
		if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
			if (preferences.getInteger("windowWidth") != 0 && preferences.getInteger("windowHeight") != 0)
				Gdx.graphics.setWindowedMode(preferences.getInteger("windowWidth"), preferences.getInteger("windowHeight"));
			else Gdx.graphics.setWindowedMode(1280, 720);
			if (preferences.getBoolean("fullscreen"))
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
	}


}

