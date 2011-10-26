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
	private final ArrayList<Pivot> pivots = new ArrayList<Pivot>();
	private String userId;
	private Direction oldDirection;
	private Direction newDirection;

	public Snake() {
	}

	public Snake(final String userId, final ArrayList<Cell> cells) {
		this.cells = cells;
		this.userId = userId;
	}

	public void addPivot(final Pivot pivot) {
		pivots.add(pivot);
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public Direction getNewDirection() {
		return newDirection;
	}

	public Direction getOldDirection() {
		return oldDirection;
	}

	public ArrayList<Pivot> getPivots() {
		return pivots;
	}

	public String getUserId() {
		return userId;
	}

	public void setNewDirection(final Direction newDirection) {
		this.newDirection = newDirection;
	}

	public void setOldDirection(final Direction oldDirection) {
		this.oldDirection = oldDirection;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

}
