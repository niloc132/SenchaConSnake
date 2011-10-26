package net.snake.client.event;

import net.snake.client.event.DirectionCommandEvent.DirectionCommandHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DirectionCommandEvent extends GwtEvent<DirectionCommandHandler> {
	public static enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
	
	private static final Type<DirectionCommandHandler> type = new Type<DirectionCommandEvent.DirectionCommandHandler>();
	public static Type<DirectionCommandHandler> getType() {
		return type;
	}
	@Override
	public Type<DirectionCommandHandler> getAssociatedType() {
		return getType();
	}
	
	private final Direction dir;
	
	public DirectionCommandEvent(Direction dir) {
		this.dir = dir;
	}
	
	public Direction getDirection() {
		return dir;
	}

	@Override
	protected void dispatch(DirectionCommandHandler handler) {
		handler.onDirectionCommand(this);
	}
	
	public interface DirectionCommandHandler extends EventHandler {
		void onDirectionCommand(DirectionCommandEvent event);
	}

}
