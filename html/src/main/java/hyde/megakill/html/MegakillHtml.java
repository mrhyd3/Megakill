package hyde.megakill.html;

import hyde.megakill.core.Megakill;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class MegakillHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Megakill();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
