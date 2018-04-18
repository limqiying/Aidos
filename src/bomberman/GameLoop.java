package bomberman;

import bomberman.constants.GlobalConstants;
import bomberman.entity.Entity;

import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop {

	static double currentGameTime;
	static double oldGameTime;
	static double deltaTime;
	final static long startNanoTime = System.nanoTime();
	private static boolean isStopped;

	private static AnimationTimer animationT;

	public static double getCurrentGameTime() {
		return currentGameTime;
	}

	public static void start(final GraphicsContext gc, Sandbox sb, double width, double height) {
		GameState.gameStatus = GlobalConstants.GameStatus.Running;
		animationT = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				oldGameTime = currentGameTime;
				currentGameTime = (currentNanoTime - startNanoTime) / 1000000000.0;
				deltaTime = currentGameTime - oldGameTime;
				gc.clearRect(0, 0, width, height);
				// TODO This will have to be something like, currentScene.getEntities()
				updateGame(sb);
				renderGame(sb);
			}
		};
		animationT.start();
		isStopped = false;
	}

	public static void stop() {
		animationT.stop();
		isStopped = true;
	}

	public static boolean isStopped() {
		return isStopped;
	}

	public static double getDeltaTime() {
		return deltaTime * 100;
	}

	public static void updateGame(Sandbox sb) {
		sb.updateEntities();
		sb.killEntities();
		cleanUpEntities(sb);
	}

	public static void renderGame(Sandbox sb) {
		sb.forEach(e -> e.draw(sb));
	}

	private static void cleanUpEntities(Sandbox sb) {
		// removes unwanted entities from the game
		Iterator<Entity> it = sb.iterator();
		while (it.hasNext()) {
			Entity entity = it.next();
			if (!entity.isPersistant()) {
				it.remove(); // not removing directly from list to prevent ConcurrentModification
			}
		}
	}

}
