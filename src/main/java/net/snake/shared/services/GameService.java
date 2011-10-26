/**
 * 
 */
package net.snake.shared.services;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Direction;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Tony.Benbrahim
 *
 */
@RemoteServiceRelativePath("GameService")
public interface GameService extends RemoteService {

	Arena joinRoom(String userId, String roomId) throws Exception;

	Arena performAction(String playerId, Direction direction) throws Exception;

	Arena startGame(String roomId) throws Exception;

}
