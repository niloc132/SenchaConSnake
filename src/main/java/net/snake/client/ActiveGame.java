package net.snake.client;

import net.snake.client.event.DirectionCommandEvent;
import net.snake.client.event.DirectionCommandEvent.DirectionCommandHandler;
import net.snake.client.widget.ArenaView;
import net.snake.client.widget.CustomViewport;
import net.snake.client.widget.SideBar;
import net.snake.shared.models.Arena;
import net.snake.shared.models.Direction;
import net.snake.shared.services.GameService;
import net.snake.shared.services.GameServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

public class ActiveGame {
	private final EventBus bus;
	private final String user;
	private final GameServiceAsync service = GWT.create(GameService.class);
	
	private Direction nextDir = null;
	
	private Arena currentArena;
	
	private final ArenaView view = new ArenaView();
	
	public ActiveGame(EventBus bus, Arena initialArena, String user) {
		this.bus = bus;
		this.currentArena = initialArena;
		this.user = user;
		
		step();
	}
	
	public void start() {
		BorderLayoutContainer root = new BorderLayoutContainer();

		// start watching keyboard commands
		Event.addNativePreviewHandler(new KeyBoardControlMonitor(bus));
		
		// create the sidebar with controls and scores
		SideBar east = new SideBar(bus, user, null);//XXX null
		BorderLayoutData eastData = new BorderLayoutData(300);
		root.setEastWidget(east, eastData);
		
		root.setCenterWidget(view);
		
		// attach everything to the page
		CustomViewport vp = new CustomViewport();
		vp.add(root);
		RootPanel.get().add(vp);
		
		
		HandlerRegistration dirHandler = bus.addHandler(DirectionCommandEvent.getType(), new DirectionCommandHandler() {
			@Override
			public void onDirectionCommand(DirectionCommandEvent event) {
				nextDir = event.getDirection();
			}
		});
	}
	
	public void stop() {
		//dirHandler.removeHandler();
	}
	
	protected void poll() {
		AsyncCallback<Arena> callback = new AsyncCallback<Arena>() {
			@Override
			public void onSuccess(Arena result) {
				currentArena = result;
				step();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("error", caught);
				step();
			}
		};
		if (nextDir == null) {
			service.poll(currentArena.getName(), callback);
		} else {
			service.performAction(user, nextDir, callback);
			
			nextDir = null;
		}
	}
	
	protected void step() {
		// tell arena view of current arena
		view.draw(currentArena);
		
		// schedule next call
		//TODO if less than $TIME sec, call right away
		new Timer() {
			@Override
			public void run() {
				poll();
			}
		}.schedule(1000);
	}
}
