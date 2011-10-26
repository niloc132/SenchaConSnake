package net.snake.client.images;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface SnakeImages extends ClientBundle {
	
	SnakeCssResource resources();
	
	@Source("UpArrow.png")
	ImageResource north();
	
	@Source("DownArrow.png")
	ImageResource south();
	
	@Source("LeftArrow.png")
	ImageResource west();
	
	@Source("RightArrow.png")
	ImageResource east();
	

}
