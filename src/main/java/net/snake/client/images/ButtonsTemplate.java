package net.snake.client.images;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface ButtonsTemplate extends XTemplates {
	
	@XTemplate(source = "buttonTemplate.html")
	SafeHtml getTemplate();

}
