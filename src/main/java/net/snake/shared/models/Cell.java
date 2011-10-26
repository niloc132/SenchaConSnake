/**
 * 
 */
package net.snake.shared.models;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Tony.Benbrahim
 *
 */
public class Cell implements IsSerializable {

	public enum CellType {
		FOOD, SNAKE, WALL
	};

	public enum Orientation {
		NORTH, SOUTH, EAST, WEST
	};

	private double width;
	private double height;
	private double x;
	private double y;
	private CellType cellType;

	private Orientation orientation;

	public Cell() {
	}

	public Cell(final double x, final double y, final double width, final double height, final Orientation orientation,
			final CellType cellType) {
		this.x = x;
		this.y = y;
		this.cellType = cellType;
		this.orientation = orientation;
		this.width = width;
		this.height = height;
	}

	public CellType getCellType() {
		return cellType;
	}

	public double getHeight() {
		return height;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public double getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setCellType(final CellType cellType) {
		this.cellType = cellType;
	}

	public void setHeight(final double height) {
		this.height = height;
	}

	public void setOrientation(final Orientation orientation) {
		this.orientation = orientation;
	}

	public void setWidth(final double width) {
		this.width = width;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

}
