package hyde.megakill.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hyde.megakill.Shapes.Asteroid;
import hyde.megakill.Shapes.Shape;

import java.util.ArrayList;

public class World {
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    ArrayList<Shape> shapes = new ArrayList<Shape>();

    public World() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true,GlobalValues.screenWidth,
                GlobalValues.screenHeight);
        shapeRenderer = new ShapeRenderer();

        for (int i=0; i<10; i++)
            addShape(new Asteroid());
    }

    public void render() {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        for ( Shape shape : shapes)
            shape.render(shapeRenderer);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
}
