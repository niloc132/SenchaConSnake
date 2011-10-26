package net.snake.client;

import net.snake.client.widget.SideBar;

import com.google.gwt.core.client.EntryPoint;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class SnakeApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		BorderLayoutContainer root = new BorderLayoutContainer();
		
		EventBus bus = new SimpleEventBus();
		//TODO add event preview for arrows, send events on bus
		
		SideBar east = new SideBar(bus);
		BorderLayoutData eastData = new BorderLayoutData(300);
		root.setEastWidget(east, eastData);
	}

}
