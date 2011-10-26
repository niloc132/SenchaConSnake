package net.snake.client.event;

import net.snake.client.event.JoinGameEvent.JoinGameHandler;
import net.snake.shared.models.Arena;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class JoinGameEvent extends GwtEvent<JoinGameHandler> {
	private static final Type<JoinGameHandler> type = new Type<JoinGameHandler>();
	
	private final Arena arena;
	private final String username;
	public JoinGameEvent(Arena arena, String username) {
		this.arena = arena;
		this.username = username;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public String getUsername() {
		return username;
	}
	
	public static Type<JoinGameHandler> getType() {
		return type;
	}
	@Override
	public Type<JoinGameHandler> getAssociatedType() {
		return getType();
	}
	@Override
	protected void dispatch(JoinGameHandler handler) {
		handler.onJoinGame(this);
	}

	public interface JoinGameHandler extends EventHandler {
		void onJoinGame(JoinGameEvent event);
	}
}
