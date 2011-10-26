package net.snake.client;

import net.snake.client.event.JoinGameEvent;
import net.snake.shared.models.Arena;
import net.snake.shared.services.GameService;
import net.snake.shared.services.GameServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class JoinGame {
	private TextField username = new TextField();
	private TextField game = new TextField();
	
	GameServiceAsync service = GWT.create(GameService.class);
	
	private EventBus bus;
	public JoinGame(EventBus bus) {
		this.bus = bus;
	}
	
	public void go() {
		final Window window = new Window();
		
		window.add(new FieldLabel(username, "Username"));
		window.add(new FieldLabel(game, "Game ID"));
		
		window.add(new TextButton("Go", new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				callServer(window);
			}
		}));
		
		window.show();
	}
	protected void callServer(Window window) {
		window.mask("Working");
		service.joinRoom(username.getValue(), game.getValue(), new AsyncCallback<Arena>() {
			
			@Override
			public void onSuccess(Arena arena) {
				bus.fireEvent(new JoinGameEvent(arena, username.getValue()));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				new MessageBox("Error", caught.getMessage());
			}
		});
	}
}
