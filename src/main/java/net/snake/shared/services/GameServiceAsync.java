/**
 * 
 */
package net.snake.shared.services;

import net.snake.server.GameEngine.Action;
import net.snake.shared.models.Arena;

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
	 * @see net.snake.shared.services.GameService#performAction(java.lang.String, net.snake.server.GameEngine.Action)
	 */
	void performAction(String playerId, Action action, AsyncCallback<Arena> callback);

	/**
	 * 
	 * @see net.snake.shared.services.GameService#startGame(java.lang.String)
	 */
	void startGame(String roomId, AsyncCallback<Arena> callback);

}
