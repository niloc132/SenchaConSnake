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
public class Arena implements IsSerializable {
	
	public enum State{ INITIALIZING, RUNNING, GAMEOVER};

	private final ArrayList<Snake> snakes = new ArrayList<Snake>();
	private final ArrayList<Cell> food = new ArrayList<Cell>();
	private State state;

	public Arena() {
		state=State.INITIALIZING;
	}

	public ArrayList<Cell> getFood() {
		return food;
	}

	public ArrayList<Snake> getSnakes() {
		return snakes;
	}

	public State getState() {
		return state;
	}

	public void setState(final State state) {
		this.state = state;
	}

}
