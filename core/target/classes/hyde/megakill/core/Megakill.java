package hyde.megakill.core;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import hyde.megakill.shapes.PlayerShape;
import hyde.megakill.input.KeyboardAndMouse;

public class Megakill implements ApplicationListener {
    World world;

    @Override
    public void create () {
        Gdx.input.setInputProcessor(new KeyboardAndMouse());
        world  = new World();
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        world.render();

    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }
}