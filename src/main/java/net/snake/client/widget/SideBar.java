package net.snake.client.widget;


import net.snake.client.event.DirectionCommandEvent;
import net.snake.client.images.ButtonsTemplate;
import net.snake.client.images.SnakeImages;
import net.snake.shared.models.Direction;
import net.snake.shared.models.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

public class SideBar implements IsWidget {
	private FlowLayoutContainer sideBar;
	private ListView<User, User> userList;
	private EventBus bus;

	public SideBar(EventBus bus, String userName, String room) {
		//TODO watch for events
		this.bus=bus;
		
		sideBar = new FlowLayoutContainer();
		
		//Label name = new Label(room);
		//sideBar.add(name);
		
		//TODO store, use current Username to be at top of list
		userList = new ListView<User, User>(null, new IdentityValueProvider<User>());
		sideBar.add(userList);
		sideBar.add(this.buildButtonsPanel());
		
		//TODO add buttons if touch
		//TODO hook up events for buttons
	}

	@Override
	public Widget asWidget() {
		return sideBar;
	}
	
	protected HtmlLayoutContainer buildButtonsPanel(){
		SnakeImages images=GWT.create(SnakeImages.class);
		images.resources().ensureInjected();
		
		IconButton north=new IconButton(images.resources().up());
		north.setWidth(48);
		north.setHeight(48);
		north.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent northEvent=new DirectionCommandEvent(Direction.NORTH);
				bus.fireEventFromSource(northEvent, this);
			}
		});
		
		IconButton south=new IconButton(images.resources().down());
		south.setWidth(48);
		south.setHeight(48);
		south.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent southEvent=new DirectionCommandEvent(Direction.SOUTH);
				bus.fireEventFromSource(southEvent, this);
				
			}
		});
		
		IconButton east=new IconButton(images.resources().right());
		east.setWidth(48);
		east.setHeight(48);

		east.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent eastEvent=new DirectionCommandEvent(Direction.EAST);
				bus.fireEventFromSource(eastEvent, this);
				
			}
		});
		
		IconButton west=new IconButton(images.resources().left());
		west.setWidth(48);
		west.setHeight(48);

		west.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent westEvent=new DirectionCommandEvent(Direction.WEST);
				bus.fireEventFromSource(westEvent, this);
				
			}
		});
		
		ButtonsTemplate template=GWT.create(ButtonsTemplate.class);
		
		HtmlLayoutContainer buttonContainer=new HtmlLayoutContainer(template.getTemplate());
		buttonContainer.add(north, new HtmlData(".top"));
		buttonContainer.add(south, new HtmlData(".bottom"));
		buttonContainer.add(east, new HtmlData(".right"));
		buttonContainer.add(west, new HtmlData(".left"));
		return buttonContainer;
	}

}
