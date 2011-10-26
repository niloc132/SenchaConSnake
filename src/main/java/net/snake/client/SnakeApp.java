package net.snake.client;

import net.snake.client.widget.CustomViewport;
import net.snake.client.widget.SideBar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Event;
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
		
		Event.addNativePreviewHandler(new KeyBoardControlMonitor(bus));
		
		
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
