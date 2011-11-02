/**
 * 
 */
package net.snake.server;

import javax.servlet.ServletException;

import net.snake.shared.models.Arena;
import net.snake.shared.models.Direction;
import net.snake.shared.services.GameService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Tony.Benbrahim
 *
 */
@SuppressWarnings("serial")
public class GameServiceImpl extends RemoteServiceServlet implements GameService {

	private GameEngine engine;

	@Override
	public void destroy() {
		try {
			engine.stop();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		engine = new GameEngine();
		engine.start();
	}

	@Override
	public Arena joinRoom(final String userId, final String roomId) throws Exception {
		engine.joinArena(roomId, userId);
		return engine.getArena(roomId);
	}

	@Override
	public Arena performAction(final String playerId, final Direction direction) throws Exception {
		engine.handleAction(playerId, direction);
		return engine.getArenaForPlayer(playerId);
	}

	@Override
	public Arena poll(final String roomId) throws Exception {
		return engine.getArena(roomId);
	}

	@Override
	public Arena startGame(final String roomId) throws Exception {
		engine.startArena(roomId);
		return engine.getArena(roomId);
	}

}
