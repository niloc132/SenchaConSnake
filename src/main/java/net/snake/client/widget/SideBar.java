package net.snake.client.widget;

import net.snake.shared.models.User;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class SideBar implements IsWidget {
	private FlowLayoutContainer sideBar;
	private ListView<User, User> userList;

	public SideBar(EventBus bus) {
		//TODO watch for events
		
		
		sideBar = new FlowLayoutContainer();
		
		
		//TODO store
		userList = new ListView<User, User>(null, new IdentityValueProvider<User>());
		sideBar.add(userList);
		
		
		//TODO add buttons if touch
		//TODO hook up events for buttons
	}

	@Override
	public Widget asWidget() {
		return sideBar;
	}

}
