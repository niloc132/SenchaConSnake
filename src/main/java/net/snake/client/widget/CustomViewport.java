package net.snake.client.widget;

import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Taken from http://www.sencha.com/forum/showthread.php?151548-Viewport-bug as a replacement for 
 * the currently broken {@link Viewport}.
 *
 */
public class CustomViewport extends SimpleContainer {

	private boolean enableScroll;

	public CustomViewport() {
		monitorWindowResize = true;
		setEnableScroll(false);
	}

	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}

	public boolean getEnableScroll() {
		return enableScroll;
	}

	public void setEnableScroll(boolean enableScroll) {
		this.enableScroll = enableScroll;
		Window.enableScrolling(enableScroll);
	}

	@Override
	protected void onAttach() {
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		super.onAttach();
	}
}
