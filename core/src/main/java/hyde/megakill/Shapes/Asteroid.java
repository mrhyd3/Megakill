package hyde.megakill.Shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Asteroid extends Shape {
    ArrayList<Vector2> points = new ArrayList<Vector2>();
    public Random random = new Random(System.currentTimeMillis());
    boolean done = false;
    int moveAmount = 0,
        moveDirection = 0,
        xSpeed = 0,
        ySpeed = 0;

    public Asteroid() {
        switch (getRandomInteger(4)) {
            case 0:
                pos.x = getRandomInteger(GlobalValues.screenWidth);
                pos.y = 5;
                break;
            case 1:
                pos.x = getRandomInteger(GlobalValues.screenWidth);
                pos.y = GlobalValues.screenHeight-5;
                break;
            case 2:
                pos.x = 5;
                pos.y = getRandomInteger(GlobalValues.screenHeight);
                break;
            case 3:
                pos.x = GlobalValues.screenWidth-5;
                pos.y = getRandomInteger(GlobalValues.screenHeight);
                break;
        }

        int numberOfPoints = getRandomInteger(6) + 4;
        Vector2 lastGeneratedPoint = new Vector2(getRandomInteger(50), getRandomInteger(50));

        for (int i=0; i<numberOfPoints - 1; i++){
            int p1 = getRandomInteger(40) + 10;
            int p2 = getRandomInteger(40) + 10;
            Iterator iterator = points.listIterator();
            while (iterator.hasNext() ) {
                Vector2 vector = (Vector2)iterator.next();
                if (p1 - vector.x > 5 ) {
                    p1 = getRandomInteger(40) + 10;
                    iterator = points.iterator();
                }
                if (p2 - vector.y > 5) {
                    p2 = getRandomInteger(40) + 10;
                    iterator = points.iterator();
                }
            }
            points.add(new Vector2(p1,p2));
        }
        done = true;
        debugPrint();
    }
    @Override
    public void render(ShapeRenderer shapeRenderer) {
        if (!done)
            return;

        if (moveAmount <= 0) {
            moveDirection = getRandomInteger(4);
            moveAmount = getRandomInteger(10)+10;
        }

            switch (getRandomInteger(8)) {
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
        shapeRenderer.end();
        System.out.println("pos = " + pos);
    }
    public int getRandomInteger(int maximum) {
        return random.nextInt(maximum);
    }
    public void debugPrint() {
        System.out.println("pos = " + pos);
        System.out.println("points = " + points);
    }
}
