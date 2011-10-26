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

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

}
