/**
 * 
 */
package net.snake.shared;

import java.util.ArrayList;

import net.snake.shared.Snake;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Tony.Benbrahim
 *
 */
public class Arena implements IsSerializable {

	private final ArrayList<Snake> snakes = new ArrayList<Snake>();

	public ArrayList<Snake> getSnakes() {
		return snakes;
	}
	
	

}
