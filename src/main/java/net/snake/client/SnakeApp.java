package net.snake.client;

import net.snake.client.event.DirectionCommandEvent;
import net.snake.client.event.DirectionCommandEvent.Direction;
import net.snake.client.widget.CustomViewport;
import net.snake.client.widget.SideBar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class SnakeApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		BorderLayoutContainer root = new BorderLayoutContainer();
		
		// build an event bus to pass around what is currently happening
		final EventBus bus = new SimpleEventBus();
		
		Event.addNativePreviewHandler(new NativePreviewHandler() {
			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				if (event.getTypeInt() == Event.ONKEYDOWN) {
					NativeEvent nativeEvent = event.getNativeEvent();
					if (KeyDownEvent.isArrow(nativeEvent.getKeyCode())) {
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
		});
		
		
		// create the sidebar with controls and scores
		SideBar east = new SideBar(bus);
		BorderLayoutData eastData = new BorderLayoutData(300);
		root.setEastWidget(east, eastData);
		
		// attach everything to the page
		CustomViewport vp = new CustomViewport();
		vp.add(root);
		RootPanel.get().add(vp);
	}

}
