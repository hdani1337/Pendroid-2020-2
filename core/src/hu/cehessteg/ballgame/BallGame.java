package hu.cehessteg.ballgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import hu.cehessteg.ballgame.Screen.IntroScreen;
import hu.cehessteg.ballgame.Stage.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class BallGame extends MyGame {
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
		try {
			preferences = Gdx.app.getPreferences("frameworkSave");
			muted = preferences.getBoolean("muted");
			Gdx.app.getGraphics().setTitle("Remember Me");
			Gdx.app.getGraphics().setResizable(false);
			setDisplay();
		}catch (NullPointerException e){
			/**Ha NullPointert kapunk, akkor még nincsenek mentett adatok**/
		}
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

