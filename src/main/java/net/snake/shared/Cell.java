/**
 * 
 */
package main.java.net.snake.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Tony.Benbrahim
 *
 */
public class Cell implements IsSerializable {
	public enum Orientation {
		NORTH, SOUTH, EAST, WEST
	};

	private double width;
	private double height;
	private Orientation orientation;

	public Cell() {
	}
	public Cell(final double width, final double height, final Orientation orientation){
		this.orientation=orientation;
		this.width=width;
		this.height=height;
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

	public void setHeight(final double height) {
		this.height = height;
	}

	public void setOrientation(final Orientation orientation) {
		this.orientation = orientation;
	}

	public void setWidth(final double width) {
		this.width = width;
	}

}
