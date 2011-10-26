package net.snake.client;

import net.snake.client.widget.CustomViewport;
import net.snake.client.widget.SideBar;
import net.snake.shared.models.Arena;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class ActiveGame {
	private final EventBus bus;
	private final String user;
	
	private Arena currentArena;
	
	public ActiveGame(EventBus bus, Arena initialArena, String user) {
		this.bus = bus;
		this.currentArena = initialArena;
		this.user = user;
	}
	
	public void start() {
		BorderLayoutContainer root = new BorderLayoutContainer();

		// start watching keyboard commands
		Event.addNativePreviewHandler(new KeyBoardControlMonitor(bus));
		
		// create the sidebar with controls and scores
		SideBar east = new SideBar(bus, user, null);//XXX null
		BorderLayoutData eastData = new BorderLayoutData(300);
		root.setEastWidget(east, eastData);
		
		// attach everything to the page
		CustomViewport vp = new CustomViewport();
		vp.add(root);
		RootPanel.get().add(vp);
	}
}
