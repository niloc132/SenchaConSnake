package net.snake.client.widget;

import net.snake.client.event.DirectionCommandEvent;
import net.snake.client.event.DirectionCommandEvent.Direction;
import net.snake.client.images.ButtonsTemplate;
import net.snake.client.images.SnakeImages;
import net.snake.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.user.client.ui.IsWidget;
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
	private IconButton north;
	private IconButton east;
	private IconButton south;
	private IconButton west;
	private EventBus bus;

	public SideBar(EventBus bus) {
		//TODO watch for events
		this.bus=bus;
		
		sideBar = new FlowLayoutContainer();
		
		
		//TODO store
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
		
		this.north=new IconButton(images.resources().up());
		this.north.setWidth(32);
		this.north.setHeight(32);
		this.north.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent northEvent=new DirectionCommandEvent(Direction.NORTH);
				bus.fireEventFromSource(northEvent, this);
			}
		});
		
		this.south=new IconButton(images.resources().down());
		this.south.setWidth(32);
		this.south.setHeight(32);
		this.south.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent southEvent=new DirectionCommandEvent(Direction.SOUTH);
				bus.fireEventFromSource(southEvent, this);
				
			}
		});
		
		this.east=new IconButton(images.resources().right());
		this.east.setWidth(32);
		this.east.setHeight(32);

		this.east.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent eastEvent=new DirectionCommandEvent(Direction.EAST);
				bus.fireEventFromSource(eastEvent, this);
				
			}
		});
		
		this.west=new IconButton(images.resources().left());
		this.west.setWidth(32);
		this.west.setHeight(32);

		this.west.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DirectionCommandEvent westEvent=new DirectionCommandEvent(Direction.WEST);
				bus.fireEventFromSource(westEvent, this);
				
			}
		});
		
		ButtonsTemplate template=GWT.create(ButtonsTemplate.class);
		
		HtmlLayoutContainer buttonContainer=new HtmlLayoutContainer(template.getTemplate());
		buttonContainer.add(north, new HtmlData(".up"));
		buttonContainer.add(south, new HtmlData("down"));
		buttonContainer.add(east, new HtmlData("right"));
		buttonContainer.add(west, new HtmlData("left"));
		return buttonContainer;
	}

}
