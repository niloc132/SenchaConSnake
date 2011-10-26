package net.snake.client.event;

import net.snake.client.event.DirectionCommandEvent.DirectionCommandHandler;
import net.snake.shared.models.Direction;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DirectionCommandEvent extends GwtEvent<DirectionCommandHandler> {
	public interface DirectionCommandHandler extends EventHandler {
		void onDirectionCommand(DirectionCommandEvent event);
	}
	private static final Type<DirectionCommandHandler> type = new Type<DirectionCommandEvent.DirectionCommandHandler>();
	public static Type<DirectionCommandHandler> getType() {
		return type;
	}
	
	private final Direction dir;
	
	public DirectionCommandEvent(final Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public Type<DirectionCommandHandler> getAssociatedType() {
		return getType();
	}

	public Direction getDirection() {
		return dir;
	}
	
	@Override
	protected void dispatch(final DirectionCommandHandler handler) {
		handler.onDirectionCommand(this);
	}

}
