package hyde.megakill.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;
import hyde.megakill.util.Random;

import java.util.ArrayList;
import java.util.Iterator;


public class Asteroid extends Shape {
    ArrayList<Vector2> points = new ArrayList<Vector2>();

    boolean done = false;
    int moveAmount = 0,
        moveDirection = 0,
        xSpeed = 0,
        ySpeed = 0;

    public Asteroid() {
        setRandomPos();
        makeAsteroidGraphics();
        done = true;
        debugPrint();
    }

    private void makeAsteroidGraphics() {
        int numberOfPointsForX = hyde.megakill.util.Random.getRandomInteger(6) + 4,
                numberOfPointsForY = hyde.megakill.util.Random.getRandomInteger(6) + 4;
        final int maxSize = 100;

        Vector2 startingPoints = new Vector2(hyde.megakill.util.Random.getRandomInteger(10),
                                             hyde.megakill.util.Random.getRandomInteger(10));

        for (int y = 1; y < maxSize+1; y+=maxSize) {
            for (int x = 1; x < numberOfPointsForX; x++) {
                int generatedX = x * Random.getRandomInteger(maxSize / numberOfPointsForX)+5,
                    generatedY = y; // * Random.getRandomInteger(3);
                points.add(new Vector2(generatedX,generatedY));
            }
        }

        for (int x = 1; x < maxSize+1; x+=maxSize) {
            for (int y = 1; y < numberOfPointsForX; y++) {
                int generatedX = x,// * Random.getRandomInteger(3),
                    generatedY = y * Random.getRandomInteger(maxSize / numberOfPointsForY )+5;
                points.add(new Vector2(generatedX,generatedY));
            }
        }
    }

    private void setRandomPos() {
        int distanceFromSide = 100;

        switch (hyde.megakill.util.Random.getRandomInteger(4)) {
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
        if (!done)
            return;

        if (moveAmount <= 0) {
            moveDirection = hyde.megakill.util.Random.getRandomInteger(4);
            moveAmount = hyde.megakill.util.Random.getRandomInteger(10)+10;
        }

            switch (hyde.megakill.util.Random.getRandomInteger(8)) {
                case 0:
                    xSpeed = 1;
                    ySpeed = 0;
                    break;
                case 1:
                    xSpeed = -1;
                    ySpeed = 0;
                    break;
                case 2:
                    xSpeed = 0;
                    ySpeed = 1;
                    break;
                case 3:
                    xSpeed = 0;
                    ySpeed = -1;
                    break;
                case 4:
                    xSpeed = 1;
                    ySpeed = 1;
                    break;
                case 5:
                    xSpeed = -1;
                    ySpeed = 1;
                    break;
                case 6:
                    xSpeed = 1;
                    ySpeed = -1;
                    break;
                case 7:
                    xSpeed = -1;
                    ySpeed = -1;
                    break;
        }
        if (moveAmount > 0) {
            pos.x += xSpeed;
            pos.y += ySpeed;
            moveAmount--;
        }

        ifOnEdgeMoveToOppositeEdge();

        shapeRenderer.identity();
        shapeRenderer.translate(pos.x,pos.y,0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1,1,1,1);

        Vector2 lastpoint = points.get(1);

        for (int i=2; i<points.size(); i++) {
            shapeRenderer.line(lastpoint.x,lastpoint.y, points.get(i).x, points.get(i).y);
            lastpoint = points.get(i);
        }

        shapeRenderer.line(lastpoint.x,lastpoint.y, points.get(0).x, points.get(0).y);

        shapeRenderer.end();
    }

    public void debugPrint() {
        System.out.println("pos = " + pos);
        System.out.println("points = " + points);
        System.out.println("points.size() = " + points.size() );
    }
}
