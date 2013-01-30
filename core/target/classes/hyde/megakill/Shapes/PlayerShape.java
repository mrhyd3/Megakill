package hyde.megakill.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;
import hyde.megakill.input.Event;
import hyde.megakill.input.KeyboardAndMouse;

import java.util.ArrayList;

public class PlayerShape extends Shape {
    private float length = 25.0f,
            rotateSpeed = 1;

    protected double speedIncrement = 0.05;

    private boolean isRotateingLeft = false,
                    isRotateingRight = false,
                    isMovingForward = false,
                    isMovingBackwards = false,
                    isShooting = false;

    private ArrayList<Missile> missiles = new ArrayList<Missile>();

    public PlayerShape() {
        pos.x = GlobalValues.screenWidth / 2;
        pos.y = GlobalValues.screenHeight / 2;

        KeyboardAndMouse keyboardAndMouse = ((KeyboardAndMouse) Gdx.input.getInputProcessor());
        keyboardAndMouse.addEvent(Input.Keys.LEFT, new Event() {
            @Override
            public void action(boolean keyDown) {
                isRotateingLeft = keyDown;
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.RIGHT, new Event() {
            @Override
            public void action(boolean keyDown) {
                isRotateingRight = keyDown;
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.UP, new Event() {
            @Override
            public void action(boolean keyDown) {
                isMovingForward = keyDown;
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.DOWN, new Event() {
            @Override
            public void action(boolean keyDown) {
                isMovingBackwards = keyDown;
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.SPACE, new Event() {
            @Override
            public void action(boolean keyDown) {
                shoot();
            }
        });
    }

    private void moveBackwards() {
        if (speed > 0) {
            speed-=speedIncrement;
            calc(false);
        }
    }

    private void moveForward() {
        speed+=speedIncrement;
        calc(true);
    }

    public void rotateLeft() {
        angle += rotateSpeed;
    }
    public void rotateRight() {
        angle -= rotateSpeed;
    }
    public void shoot() {
        System.out.println("Shoot");
        missiles.add(new Missile(pos.x, pos.y, angle, speed));
    }

    @Override
    public String toString() {
        return "PlayerShape{" +
                "length=" + length +
                ", rotateSpeed=" + rotateSpeed +
                ", speedIncrement=" + speedIncrement +
                ", isRotateingLeft=" + isRotateingLeft +
                ", isRotateingRight=" + isRotateingRight +
                ", isMovingForward=" + isMovingForward +
                ", isMovingBackwards=" + isMovingBackwards +
                ", isShooting=" + isShooting +
                '}';
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        ifOnEdgeMoveToOppositeEdge();
        moveAndRotateShip();

        pos.x += xForwardMomentum;
        pos.y += yForwardMomentum;

        shapeRenderer.identity();
        shapeRenderer.translate(pos.x, pos.y, 0);
        shapeRenderer.rotate(0, 0, 1, angle);

        shapeRenderer.translate(-(length / 2),-(length / 2), 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Triangle);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.triangle(0,0,length,0,length/2,length);
        shapeRenderer.end();

        for (Missile missile : missiles)
            missile.render(shapeRenderer);
    }

    private void moveAndRotateShip() {
        if (isRotateingLeft)
            rotateLeft();
        if (isRotateingRight)
            rotateRight();
        if (isMovingBackwards)
            moveBackwards();
        if (isMovingForward)
            moveForward();
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

}