package bomberman;

import bomberman.constants.GlobalConstants;
import javafx.scene.Scene;

/**
 * Responsible for updating the sandbox and loading new maps as the player
 * advances through levels Holds player statistics such as points, lives, etc
 * 
 * @author tialim
 *
 */

public class GameHandler {

	MapLoader mapL = new MapLoader();
	WindowManager w = new WindowManager(this);
	Sandbox sb;
	GameLoop loop = new GameLoop();

	public GameHandler() {
		newGame();
	}

	public Scene getScene() {
		return w.getScene();
	}

	public void newGame() {
		mapL.setLevel(1);
		double sceneW = mapL.getSceneWidth();
		double sceneH = mapL.getSceneHeight();
		w.resetCanvas(sceneW, sceneH);
		sb = new Sandbox(GlobalConstants.parent, mapL.getEntities(), w.getGraphicsContext());
		loop.init(w.getGraphicsContext(), sb, sceneW, sceneH);
		loop.start();
	}
	
	public void stopGame() {
		loop.stop();
	}
	
	public void resumeGame() {
		loop.start();
	}
}
