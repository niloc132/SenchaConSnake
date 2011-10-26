/**
 * 
 */
package net.snake.server;

import java.util.HashMap;
import java.util.Map;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Cell;
import net.snake.shared.models.Pivot;
import net.snake.shared.models.Snake;

/**
 * @author Tony.Benbrahim
 *
 */
public class GameEngine {

	public enum Action {
		UP, DOWN, LEFT, RIGHT
	}

	protected static final long LOOP_DELAY = 20;

	private final Map<String, Arena> arenas = new HashMap<String, Arena>();
	private final Map<String, Snake> userSnake = new HashMap<String, Snake>();
	private final Map<String, Arena> userArenas = new HashMap<String, Arena>();

	private final Object arenaLock = new Object();
	private final Object snakeLock = new Object();
	private boolean interrupted;
	private Thread thread;

	public GameEngine() {
	}

	/**
	 * builds an empty arena with no snakes
	 * @param roomId the roomId to create
	 */
	public void buildArena(final String roomId) {
		final Arena arena = new Arena();
		arenas.put(roomId, arena);
	}

	public void handleAction(final String roomId, final String playerId, final Action action) {
		final Arena arena = getArena(roomId);
		final Snake snake = getSnake(playerId);
		final Cell headCell = snake.getCells().get(0);
		snake.addPivot(new Pivot(headCell.getX(), headCell.getY()));
	}

	/**
	 * lets a player join an arena, and creates a snake for the arena
	 * @param roomId the room id to join
	 * @param playerId
	 */
	public void joinArena(final String roomId, final String playerId) {
		final Arena arena = getArena(roomId);
		final Snake snake = new Snake();
		synchronized (arenaLock) {
			userArenas.put(playerId, arena);
			synchronized (snakeLock) {
				userSnake.put(playerId, snake);
			}
		}
	}

	public void start() {
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while(!interrupted){
					try {

						
						Thread.sleep(LOOP_DELAY);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		interrupted = false;
		thread = new Thread(runnable, "GameEngine");
		thread.start();
	}

	public void stop() throws InterruptedException {
		interrupted = true;
		thread.wait();
	}

	/**
	 * @param roomId
	 * @return
	 */
	private synchronized Arena getArena(final String roomId) {
		final Arena arena = arenas.get(roomId);
		if (arena == null) {
			throw new IllegalArgumentException("Invalid room id");
		}
		return arena;
	}

	/**
	 * @param playerId
	 * @return
	 */
	private synchronized Snake getSnake(final String playerId) {
		final Snake snake = userSnake.get(playerId);
		if (snake == null) {
			throw new IllegalArgumentException("no snake assigned to player");
		}
		return snake;
	}

}