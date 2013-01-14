package hyde.megakill.java;

import hyde.megakill.core.Megakill;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MegakillDesktop {
	public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.useGL20 = true;
        config.forceExit = true;
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new Megakill(), config);
	}
}
