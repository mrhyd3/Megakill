package hyde.megakill.Shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;


public class Asteroid extends Shape {
    ArrayList<Vector2> points = new ArrayList<Vector2>();
    public static final Random random = new Random(System.currentTimeMillis());

    public Asteroid() {
        int numberOfPoints = getRandomInteger(10);
        Vector2 lastGeneratedPoint = new Vector2(getRandomInteger(50), getRandomInteger(50));
        for (int i=0; i<numberOfPoints; i++){
            
        }
    }
    @Override
    public void render(ShapeRenderer shapeRenderer) {

    }
    public static int getRandomInteger(int maximum) {
        return random.nextInt(maximum);
    }
    public static float getRandomFloat(float minimum, float maximum) {
        return random.nextFloat() * maximum + minimum;
    }
}
