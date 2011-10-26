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

	public enum State {
		INITIALIZING, RUNNING, GAMEOVER
	};

	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	private ArrayList<Cell> food = new ArrayList<Cell>();
	private State state;
	private String name;

	public Arena() {
		state = State.INITIALIZING;
	}

	public ArrayList<Cell> getFood() {
		return food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Snake> getSnakes() {
		return snakes;
	}

	public State getState() {
		return state;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setState(final State state) {
		this.state = state;
	}

}
