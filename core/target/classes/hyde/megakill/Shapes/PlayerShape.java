package hyde.megakill.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hyde.megakill.core.GlobalValues;
import hyde.megakill.input.Event;
import hyde.megakill.input.KeyboardAndMouse;

public class PlayerShape extends Shape {
    private float length = 25.0f,
            rotateSpeed = 1;

    private double xForwardMomentum = 0,
            yForwardMomentum = 0,
            speed = 0,
            speedIncrement = 0.1;

    private int angle = 0,
            angleFromLastForwardThrust = 0;

    private boolean isRotateingLeft = false,
                    isRotateingRight = false,
                    isMovingForward = false,
                    isMovingBackwards = false,
                    isShooting = false;

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
                isShooting = keyDown;
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

    }
    private void calc(boolean changeAngle) {
        if (changeAngle) {
            xForwardMomentum = Math.sin(Math.toRadians(-angle)) * speed;
            yForwardMomentum = Math.cos(Math.toRadians(-angle)) * speed;
            angleFromLastForwardThrust = angle;
        } else {
            xForwardMomentum = Math.sin(Math.toRadians(-angleFromLastForwardThrust)) * speed;
            yForwardMomentum = Math.cos(Math.toRadians(-angleFromLastForwardThrust)) * speed;
        }
    }
    private void debugPrint() {
        System.out.println("xForwardMomentum = " + xForwardMomentum);
        System.out.println("yForwardMomentum = " + yForwardMomentum);
        System.out.println("speed = " + speed);
        System.out.println("angle = " + angle);
        System.out.println("pos = " + pos);
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
        /*shapeRenderer.line(0,0,length,0);
        shapeRenderer.line(length,0,length/2,length);
        shapeRenderer.line(length/2,length,0,0);*/
        shapeRenderer.end();


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


}