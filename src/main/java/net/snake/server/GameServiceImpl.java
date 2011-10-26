/**
 * 
 */
package net.snake.server;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Direction;
import net.snake.shared.services.GameService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Tony.Benbrahim
 *
 */
public class GameServiceImpl  extends RemoteServiceServlet implements GameService{

	/* (non-Javadoc)
	 * @see net.snake.shared.services.GameService#joinRoomlogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Arena joinRoom(final String userId, final String roomId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.snake.shared.services.GameService#performAction(java.lang.String, net.snake.server.GameEngine.Action)
	 */
	@Override
	public Arena performAction(final String playerId, final Direction direction) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.snake.shared.services.GameService#startGame(java.lang.String)
	 */
	@Override
	public Arena startGame(final String roomId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
