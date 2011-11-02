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

import com.sencha.gxt.core.client.util.PreciseRectangle;

/**
 * @author Tony.Benbrahim
 *
 */
public class GameEngine {

	class PreciseRectangleEx extends PreciseRectangle {

		public PreciseRectangleEx(final double x, final double y, final double width, final double height) {
			super(x, y, width, height);
		}

		public boolean intersects(final PreciseRectangle other) {
			final boolean intersectX = (this.getX() >= other.getX() && this.getX() < other.getX() + other.getWidth())
					|| (other.getX() > this.getX() && other.getX() < this.getX() + this.getWidth());
			final boolean intersectY = (this.getY() >= other.getY() && this.getY() < other.getY() + other.getHeight())
					|| (other.getY() > this.getY() && other.getY() < this.getY() + this.getHeight());
			return intersectX && intersectY;
		}
	}

	protected static final long LOOP_DELAY = 100;
	protected static final double DELTA = 0.025;
	private static final long POINTS_TURN = 1L;
	private static final long POINTS_FOOD = 100L;

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
		arena.setName(roomId);
		synchronized (arenas) {
			arenas.put(roomId, arena);
			createFood(arena);
			createFood(arena);
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
			snake.setDirection(direction);
		}
	}

	/**
	 * lets a player join an arena, and creates a snake for the arena
	 * @param roomId the room id to join
	 * @param playerId
	 */
	public void joinArena(final String roomId, final String playerId) {
		 Arena arena;
		try {
			arena = getArena(roomId);
		} catch (IllegalArgumentException ex) {
			buildArena(roomId);
			
			arena = getArena(roomId);
		}
		int index;
		synchronized (arena) {
			index = arena.getSnakes().size();
		}
		final ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int i = 0; i < 5; ++i) {
			final double x = 0.25d + index * 0.20d;
			final double y = 0.25d + 0.02d * i;
			final Cell cell = new Cell(x, y, 0.02d, 0.02d, Direction.NORTH, Cell.CellType.SNAKE);
			cells.add(cell);
		}
		final Snake snake = new Snake(playerId, cells);
		snake.setDirection(Direction.NORTH);
		arena.getSnakes().add(snake);
		synchronized (userArenas) {
			userArenas.put(playerId, arena);
			synchronized (userSnake) {
				userSnake.put(playerId, snake);
			}
		}
	}

	public void start() {
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (!interrupted) {
					try {
						synchronized (arenas) {
							for (final Arena arena : arenas.values()) {
								synchronized (arena) {
									if (arena.getState() == State.RUNNING) {
										processArena(arena);
									}
								}
							}
						}
						Thread.sleep(LOOP_DELAY);
					} catch (final InterruptedException e) {
						break;
					} catch (final Exception e) {
						continue;
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
	 * @param arena
	 */
	protected void processArena(final Arena arena) {
		for (final Snake snake : arena.getSnakes()) {
			if (snake.isAlive()) {
				synchronized (snake) {
					final Cell nextCell = new Cell(0, 0, 0.02d, 0.02d, Direction.NORTH, Cell.CellType.SNAKE);
					nextCell.setDirection(snake.getDirection());
					final Cell headCell = snake.getCells().get(0);
					switch (nextCell.getDirection()) {
					case EAST:
						nextCell.setX(headCell.getX() + headCell.getWidth());
						nextCell.setY(headCell.getY());
						break;
					case NORTH:
						nextCell.setX(headCell.getX());
						nextCell.setY(headCell.getY() - headCell.getHeight());
						break;
					case WEST:
						nextCell.setX(headCell.getX() - headCell.getWidth());
						nextCell.setY(headCell.getY());
						break;
					case SOUTH:
						nextCell.setX(headCell.getX());
						nextCell.setY(headCell.getY() + headCell.getHeight());
						break;
					}
					if (!checkWallCollision(arena, nextCell) && !checkSnakeCollision(arena, nextCell)) {
						snake.getCells().add(0, nextCell);
						snake.setScore(snake.getScore() + POINTS_TURN);
						if (checkFoodCollision(arena, nextCell)) {
							snake.setScore(snake.getScore() + POINTS_FOOD);
							createFood(arena);
						} else {
							snake.getCells().remove(snake.getCells().size() - 1);
						}
					} else {
						kill(arena, snake);
					}
				}
			}
		}
		int numAlive = 0;
		for (final Snake snake : arena.getSnakes()) {
			if (snake.isAlive()) {
				++numAlive;
			}
		}
		if (numAlive == 0) {
			arena.setState(State.GAMEOVER);
		}
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
	 * @param snake
	 * @return
	 */
	private boolean checkFoodCollision(final Arena arena, final Cell cell) {
		final PreciseRectangleEx cellRect = new PreciseRectangleEx(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
		for (final Cell foodCell : arena.getFood()) {
			final PreciseRectangleEx snakeRect = new PreciseRectangleEx(foodCell.getX(), foodCell.getY(), foodCell.getWidth(),
					foodCell.getHeight());
			if (cellRect.intersects(snakeRect)) {
				arena.getFood().remove(foodCell);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param arena
	 * @param cell
	 * @return
	 */
	private boolean checkSnakeCollision(final Arena arena, final Cell cell) {
		final PreciseRectangleEx cellRect = new PreciseRectangleEx(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
		for (final Snake snake : arena.getSnakes()) {
			if (snake.isAlive()) {
				for (final Cell snakeCell : snake.getCells()) {
					final PreciseRectangleEx snakeRect = new PreciseRectangleEx(snakeCell.getX(), snakeCell.getY(),
							snakeCell.getWidth(), snakeCell.getHeight());
					if (cellRect.intersects(snakeRect)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param arena
	 * @param cell
	 * @return
	 */
	private boolean checkWallCollision(final Arena arena, final Cell cell) {
		return cell.getX() < 0 || cell.getY() < 0 || cell.getX() > 1 || cell.getY() > 1;
	}

	private void createFood(final Arena arena) {
		final Cell food = new Cell();
		food.setWidth(0.02d);
		food.setHeight(0.02d);
		do {
			food.setX(Math.random());
			food.setY(Math.random());
		} while (checkSnakeCollision(arena, food));
		arena.getFood().add(food);
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

	/**
	 * @param arena
	 * @param snake
	 */
	private void kill(final Arena arena, final Snake snake) {
		snake.setAlive(false);
	}
}