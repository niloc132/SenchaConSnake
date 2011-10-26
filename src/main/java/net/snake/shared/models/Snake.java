/**
 * 
 */
package net.snake.shared.models;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Tony.Benbrahim
 *
 */
public class Snake implements IsSerializable {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private String userId;
	private Direction newDirection;
	private boolean alive;
	private long score;

	public Snake() {
		alive = true;
	}

	public Snake(final String userId, final ArrayList<Cell> cells) {
		this();
		this.cells = cells;
		this.userId = userId;
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public Direction getDirection() {
		return newDirection;
	}

	public Direction getNewDirection() {
		return newDirection;
	}

	public long getScore() {
		return score;
	}

	public String getUserId() {
		return userId;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(final boolean alive) {
		this.alive = alive;
	}

	public void setCells(final ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public void setDirection(final Direction newDirection) {
		this.newDirection = newDirection;
	}

	public void setNewDirection(final Direction newDirection) {
		this.newDirection = newDirection;
	}

	public void setScore(final long score) {
		this.score = score;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

}
