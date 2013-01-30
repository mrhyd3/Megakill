package hyde.megakill.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;
import hyde.megakill.util.Random;

import java.util.ArrayList;


public class Asteroid extends Shape {
    ArrayList<Vector2> points = new ArrayList<Vector2>();

    int moveAmount = 0,
        moveDirection = 0,
        xSpeed = 0,
        ySpeed = 0;

    public Asteroid() {
        setRandomPos();
        angle = Random.getRandomInteger(360);
        speed = Random.getRandomInteger(10);
        speed += 1;
        speed /= 10;
        makeAsteroidGraphics();

    }

    private void makeAsteroidGraphics() {
        int numberOfPoints = Random.getRandomInteger(6)+6;
        makeCircle(numberOfPoints);
        for ( Vector2 point : points) {
            point.x += Random.getRandomInteger(10);
            point.y += Random.getRandomInteger(10);
        }
    }

    private void makeCircle(int numberOfPoints) {
        for (double angle=0; angle < 360; angle += 360 / numberOfPoints) {
             float x = (float)Math.sin(Math.toRadians(angle))*10,
                   y = (float)Math.cos(Math.toRadians(angle))*10;
             points.add(new Vector2(x,y));
        }
    }

    private void setRandomPos() {
        int distanceFromSide = 0;

        switch (Random.getRandomInteger(4)) {
            case 0:
                pos.x = hyde.megakill.util.Random.getRandomInteger(GlobalValues.screenWidth - distanceFromSide) + distanceFromSide;
                pos.y = distanceFromSide;
                break;
            case 1:
                pos.x = hyde.megakill.util.Random.getRandomInteger(GlobalValues.screenWidth - distanceFromSide) + distanceFromSide ;
                pos.y = GlobalValues.screenHeight-distanceFromSide;
                break;
            case 2:
                pos.x = distanceFromSide;
                pos.y = hyde.megakill.util.Random.getRandomInteger(GlobalValues.screenHeight - distanceFromSide) + distanceFromSide;
                break;
            case 3:
                pos.x = GlobalValues.screenWidth-distanceFromSide;
                pos.y = hyde.megakill.util.Random.getRandomInteger(GlobalValues.screenHeight - distanceFromSide) + distanceFromSide;
                break;
        }
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        calc(true);

        ifOnEdgeMoveToOppositeEdge();
        createLinesBetweenPoints(shapeRenderer);
    }

    private void createLinesBetweenPoints(ShapeRenderer shapeRenderer) {
        if (isDestroyed) {
            double angle=0;
            for (int i=0; i<points.size(); i+=1) {
                points.get(i).x -= Math.sin(Math.toRadians(-angle)) * 1;
                points.get(i).y -= Math.cos(Math.toRadians(-angle)) * 1;
                angle += 360 / points.size();
            }
        }
        shapeRenderer.identity();
        shapeRenderer.translate(pos.x,pos.y,0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1,1,1,1);

        Vector2 lastpoint = points.get(0);

        for (int i=1; i<points.size(); i++) {
            shapeRenderer.line(lastpoint.x,lastpoint.y, points.get(i).x, points.get(i).y);
            lastpoint = points.get(i);
        }

        shapeRenderer.line(lastpoint.x,lastpoint.y, points.get(0).x, points.get(0).y);

        shapeRenderer.end();
    }


    @Override
    public String toString() {
        return super.toString() +
                "Asteroid{" +
                "points=" + points +
                ", moveAmount=" + moveAmount +
                ", moveDirection=" + moveDirection +
                ", xSpeed=" + xSpeed +
                ", ySpeed=" + ySpeed +
                '}';
    }
}
