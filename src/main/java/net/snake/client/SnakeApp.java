package net.snake.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class SnakeApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		// build an event bus to pass around what is currently happening
		final EventBus bus = new SimpleEventBus();
		
		
		ActiveGame game = new ActiveGame(bus);
		game.start("me", "mygame");
	}

}
