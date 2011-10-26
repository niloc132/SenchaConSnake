/**
 * 
 */
package net.snake.server;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Tony.Benbrahim
 *
 */
public class Pivot implements IsSerializable {

	private double x;
	private double y;
	
	public Pivot(){
	}

	public Pivot(final double x, final double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

}
