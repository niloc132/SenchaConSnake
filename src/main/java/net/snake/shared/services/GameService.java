/**
 * 
 */
package net.snake.shared.services;

import net.snake.server.GameEngine.Action;
import net.snake.shared.models.Arena;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Tony.Benbrahim
 *
 */
@RemoteServiceRelativePath("GameService")
public interface GameService extends RemoteService {

	Arena joinRoom(String userId, String roomId) throws Exception;

	Arena performAction(String playerId, Action action) throws Exception;

	Arena startGame(String roomId) throws Exception;

}
