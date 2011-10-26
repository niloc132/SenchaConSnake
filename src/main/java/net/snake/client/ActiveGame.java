package net.snake.client;

import net.snake.client.widget.CustomViewport;
import net.snake.client.widget.SideBar;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class ActiveGame {
	private final EventBus bus;
	public ActiveGame(EventBus bus) {
		this.bus = bus;
	}
	
	public void start(String user, String gameId) {
		BorderLayoutContainer root = new BorderLayoutContainer();

		// start watching keyboard commands
		Event.addNativePreviewHandler(new KeyBoardControlMonitor(bus));
		
		// create the sidebar with controls and scores
		SideBar east = new SideBar(bus, user, gameId);
		BorderLayoutData eastData = new BorderLayoutData(300);
		root.setEastWidget(east, eastData);
		
		// attach everything to the page
		CustomViewport vp = new CustomViewport();
		vp.add(root);
		RootPanel.get().add(vp);
	}
}
