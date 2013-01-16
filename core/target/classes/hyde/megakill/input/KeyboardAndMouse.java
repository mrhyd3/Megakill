package hyde.megakill.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.Map;



public class KeyboardAndMouse implements InputProcessor {
    private HashMap<Integer, Event> actions = new HashMap<Integer, Event>();

    public KeyboardAndMouse() {
        actions.put(Input.Keys.ESCAPE,new Event() {
            @Override
            public void action(boolean keyDown) {
                Gdx.app.exit();
            }
        });
    }
    public void addEvent(int keycode, Event event) {
        if (actions.get(keycode) != null)
            System.out.println("Key: " + keycode + " already bound by " +
                    actions.get(keycode));
        else
            actions.put(keycode, event);
    }
    public void addEvents(HashMap<Integer, Event> actions) {
        for (Map.Entry<Integer, Event> entry : actions.entrySet())
            actions.put(entry.getKey(), entry.getValue());
    }
    @Override
    public boolean keyDown(int keycode) {
        if (actions.get(keycode) != null ) {
            actions.get(keycode).action(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (actions.get(keycode) != null ) {
            actions.get(keycode).action(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
