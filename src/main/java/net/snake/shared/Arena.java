/**
 * 
 */
package net.snake.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Tony.Benbrahim
 *
 */
public class Arena implements IsSerializable {

	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	private ArrayList<Cell> food = new ArrayList<Cell>();

	public Arena() {
	}

	public Arena(final ArrayList<Snake> snakes, final ArrayList<Cell> food) {
		this.snakes = snakes;
		 this.food=food;
	}

	public ArrayList<Cell> getFood() {
		return food;
	}

	public ArrayList<Snake> getSnakes() {
		return snakes;
	}

}
