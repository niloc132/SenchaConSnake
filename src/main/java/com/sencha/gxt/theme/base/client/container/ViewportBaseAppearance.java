package com.sencha.gxt.theme.base.client.container;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.widget.core.client.container.Viewport.ViewportAppearance;

/**
 * Dirty hack to make viewport not poison the build
 * @author colin
 *
 */
public class ViewportBaseAppearance implements ViewportAppearance {
	public ViewportBaseAppearance() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render(SafeHtmlBuilder sb) {
		sb.appendEscaped("");
	}

}
