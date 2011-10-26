package net.snake.client.event;

import net.snake.client.event.ArenaTickEvent.ArenaTickHandler;
import net.snake.shared.models.Arena;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ArenaTickEvent extends GwtEvent<ArenaTickHandler> {
	private static GwtEvent.Type<ArenaTickHandler> TYPE = new GwtEvent.Type<ArenaTickHandler>();
	public static GwtEvent.Type<ArenaTickHandler> getType() {
		return TYPE;
	}
	@Override
	public GwtEvent.Type<ArenaTickHandler> getAssociatedType() {
		return getType();
	}
	private final Arena arena;
	public ArenaTickEvent(Arena arena) {
		this.arena = arena;
	}
	public Arena getArena() {
		return arena;
	}
	
	@Override
	protected void dispatch(ArenaTickHandler handler) {
		handler.onTick(this);
	}
	public interface ArenaTickHandler extends EventHandler {
		void onTick(ArenaTickEvent event);
	}
}
