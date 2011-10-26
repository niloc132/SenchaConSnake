/**
 * 
 */
package net.snake.shared.services;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Direction;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Tony.Benbrahim
 *
 */
public interface GameServiceAsync {

	/**
	 * 
	 * @see net.snake.shared.services.GameService#joinRoom(java.lang.String, java.lang.String)
	 */
	void joinRoom(String userId, String roomId, AsyncCallback<Arena> callback);

	/**
	 * 
	 * @see net.snake.shared.services.GameService#performAction(java.lang.String, net.snake.shared.models.Direction)
	 */
	void performAction(String playerId, Direction direction, AsyncCallback<Arena> callback);

	void poll(String roomId, AsyncCallback<Arena> callback);

	/**
	 * 
	 * @see net.snake.shared.services.GameService#startGame(java.lang.String)
	 */
	void startGame(String roomId, AsyncCallback<Arena> callback);

}
