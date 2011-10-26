package net.snake.client;

import net.snake.client.event.JoinGameEvent;
import net.snake.client.event.JoinGameEvent.JoinGameHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class SnakeApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		// build an event bus to pass around what is currently happening
		final EventBus bus = new SimpleEventBus();
		
		bus.addHandler(JoinGameEvent.getType(), new JoinGameHandler() {
			@Override
			public void onJoinGame(JoinGameEvent event) {
				ActiveGame game = new ActiveGame(bus, event.getArena(), event.getUsername());
				game.start();
			}
		});
		
		JoinGame dialog = new JoinGame(bus);
		
		dialog.go();
		
		

	}

}
