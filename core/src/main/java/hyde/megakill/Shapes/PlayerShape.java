package hyde.megakill.Shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;
import hyde.megakill.input.Event;
import hyde.megakill.input.KeyboardAndMouse;

public class PlayerShape implements Shape {
    private float length = 25.0f,
            rotateSpeed = 5;

    private double Xspeed = 0, Yspeed = 0;

    private int speed = 0,
            angle = 0;

    private Vector2 pos = new Vector2(GlobalValues.screenWidth / 2,
            GlobalValues.screenHeight / 2);

    public PlayerShape() {
        KeyboardAndMouse keyboardAndMouse = ((KeyboardAndMouse) Gdx.input.getInputProcessor());
        keyboardAndMouse.addEvent(Input.Keys.LEFT, new Event() {
            @Override
            public void action() {
                rotateLeft();
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.RIGHT, new Event() {
            @Override
            public void action() {
                rotateRight();
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.UP, new Event() {
            @Override
            public void action() {
                moveForward();
            }
        });
        keyboardAndMouse.addEvent(Input.Keys.BACK, new Event() {
            @Override
            public void action() {
                moveBackwards();
            }
        });
    }

    private void moveBackwards() {
        if (speed > 0) {
            speed--;
            //calc();
            Xspeed--;
            Yspeed--;
        }
    }

    private void moveForward() {
        speed++;
        calc();
    }

    public void rotateLeft() {
        angle += rotateSpeed;
    }
    public void rotateRight() {
        angle -= rotateSpeed;
    }
    private void calc() {
        Xspeed = Math.sin(Math.toRadians(angle)) * speed;
        Yspeed = Math.cos(Math.toRadians(angle)) * speed;

         /* facing = angle.toRadians
        Xforce = sin(facing) * acceleration
        Yforce = cos(facing) * acceleration

        speed = Sqr((Xspeed + Xforce)^2 + (Yspeed + Yforce)^2)
        direction = Atn((Xspeed + Xforce)/(Yspeed + Yforce))
        if (Yspeed + Yforce < 0) direction = direction + Pi
         */
    }
    @Override
    public void render(ShapeRenderer shapeRenderer) {
        ifOnEdgeMoveToOppositeEdge();

        pos.x += Xspeed;
        pos.y += Yspeed;

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

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,1,0,1);
        shapeRenderer.line(length / 2, 0, length, 0);
        shapeRenderer.end();

    }

    private void ifOnEdgeMoveToOppositeEdge() {
        if (pos.x > GlobalValues.screenWidth)
            pos.x = 0;
        if (pos.x < 0)
            pos.x = GlobalValues.screenWidth;
        if (pos.y > GlobalValues.screenHeight)
            pos.y = 0;
        if (pos.y < 0)
            pos.y = GlobalValues.screenHeight;
    }
}