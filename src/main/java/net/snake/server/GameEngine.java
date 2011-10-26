/**
 * 
 */
package net.snake.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Arena.State;
import net.snake.shared.models.Cell;
import net.snake.shared.models.Direction;
import net.snake.shared.models.Snake;

/**
 * @author Tony.Benbrahim
 *
 */
public class GameEngine {

	protected static final long LOOP_DELAY = 20;

	private final Map<String, Arena> arenas = new HashMap<String, Arena>();
	private final Map<String, Snake> userSnake = new HashMap<String, Snake>();
	private final Map<String, Arena> userArenas = new HashMap<String, Arena>();

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
		synchronized (arenas) {
			arenas.put(roomId, arena);
		}
	}

	/**
	 * @param playerId
	 * @return
	 */
	public Arena getArenaForPlayer(final String playerId) {
		synchronized (userArenas) {
			final Arena arena = userArenas.get(playerId);
			if (arena == null) {
				throw new IllegalArgumentException("No room for player");
			}
			return arena;
		}
	}

	public void handleAction(final String playerId, final Direction direction) {
		final Snake snake = getSnake(playerId);
		if (snake == null) {
			throw new IllegalArgumentException("player does not have a snake");
		}
		synchronized (snake) {
			snake.setNewDirection(direction);
		}
	}

	/**
	 * lets a player join an arena, and creates a snake for the arena
	 * @param roomId the room id to join
	 * @param playerId
	 */
	public void joinArena(final String roomId, final String playerId) {
		final Arena arena = getArena(roomId);
		int index;
		synchronized (arena) {
			index = arena.getSnakes().size();
		}
		final ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int i = 0; i < 5; ++i) {
			final double x = 0.25d + index * 0.20d;
			final double y = 0.25d + 0.05d * i;
			final Cell cell = new Cell(x, y, 0.05d, 0.05d, Cell.Orientation.NORTH, Cell.CellType.SNAKE);
			cells.add(cell);
		}
		final Snake snake = new Snake(playerId, cells);
		synchronized (userArenas) {
			userArenas.put(playerId, arena);
			synchronized (userSnake) {
				userSnake.put(playerId, snake);
			}
		}
	}

	public void setDirection(final String playerId, final Direction direction) {
		final Snake snake = getSnake(playerId);
		synchronized (snake) {
			snake.setNewDirection(direction);
		}
	}

	public void start() {
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (!interrupted) {
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

	/**
	 * @param roomId
	 */
	public void startAreana(final String roomId) {
		final Arena arena = getArena(roomId);
		if (arena.getState() == State.INITIALIZING || arena.getState() == State.GAMEOVER) {
			synchronized (arena) {
				arena.setState(State.RUNNING);
			}
		}

	}

	public void stop() throws InterruptedException {
		interrupted = true;
		thread.wait();
	}

	/**
	 * @param roomId
	 * @return
	 */
	synchronized Arena getArena(final String roomId) {
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