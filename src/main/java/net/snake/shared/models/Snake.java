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

	public Snake() {
		alive=true;
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

	public void setDirection(final Direction newDirection) {
		this.newDirection = newDirection;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

}
