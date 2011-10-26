package net.snake.client;

import net.snake.client.event.DirectionCommandEvent;
import net.snake.client.event.DirectionCommandEvent.Direction;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Watches the native browser events for arrow keys, and sends those out as events to be consumed
 * by the rest of the app
 *
 */
public final class KeyBoardControlMonitor implements NativePreviewHandler {
	private final EventBus bus;

	/**
	 * Creates a monitor for keyboard events. Call {@link Event#addNativePreviewHandler(NativePreviewHandler)} to watch for arrow keys, and remove it when finished.
	 * @param bus the event bus the direction commands should be sent on
	 */
	public KeyBoardControlMonitor(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		if (event.getTypeInt() == Event.ONKEYDOWN) {
			NativeEvent nativeEvent = event.getNativeEvent();
			if (KeyDownEvent.isArrow(nativeEvent.getKeyCode())) {
				event.cancel();
				final Direction d;
				switch(nativeEvent.getKeyCode()) {
				case KeyCodes.KEY_DOWN:
					d = Direction.SOUTH;
					break;
				case KeyCodes.KEY_UP:
					d = Direction.NORTH;
					break;
				case KeyCodes.KEY_LEFT:
					d = Direction.WEST;
					break;
				case KeyCodes.KEY_RIGHT:
					d = Direction.EAST;
					break;
				default:
					assert false : "That wasn't a direction, check isArray()";
					d = null;
				}
				
				bus.fireEvent(new DirectionCommandEvent(d));
			}
		}
	}
}