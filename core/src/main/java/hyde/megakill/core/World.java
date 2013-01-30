package hyde.megakill.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import hyde.megakill.shapes.Asteroid;
import hyde.megakill.shapes.Missile;
import hyde.megakill.shapes.PlayerShape;
import hyde.megakill.shapes.Shape;

import java.util.ArrayList;

public class World {
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    ArrayList<Shape> asteroids = new ArrayList<Shape>();
    PlayerShape player = new PlayerShape();
    boolean gameOver = false;

    public World() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true,GlobalValues.screenWidth,
                GlobalValues.screenHeight);
        shapeRenderer = new ShapeRenderer();

        for (int i=0; i<10; i++) {
            Asteroid asteroid = new Asteroid();
            System.out.println(asteroid.toString());
            addShape(asteroid);
        }
    }

    public void render() {
        camera.update();
        checkCollisions();
        shapeRenderer.setProjectionMatrix(camera.combined);
        if (!gameOver) {
            player.render(shapeRenderer);
            for ( Shape shape : asteroids)
                shape.render(shapeRenderer);
        } else {

        }
    }

    private void checkCollisions() {
        for ( Shape asteroid : asteroids) {
            if ( collides(player.getPos().x, player.getPos().y, 25, 
                    asteroid.getPos().x, asteroid.getPos().y, 25) )
                System.out.println("player collided with asteroid");


        }
        for (Missile missile : player.getMissiles() ) {
            for ( Shape asteroid : asteroids) {
                if ( collides(asteroid.getPos().x, asteroid.getPos().y, 25,
                        missile.getPos().x, missile.getPos().y, 6) ) {
                    System.out.println("missile collided with asteroid");
                }
            }
        }
    }

    public void addShape(Shape shape) {
        asteroids.add(shape);
    }
   
    public boolean collides(float x1,float y1,float radius1,float x2,float y2,float radius2) {
        
        float dx = x2 - x1;
        float dy = y2 - y1;
        float radii = radius1 + radius2;

        return (dx * dx) + (dy * dy) < radii * radii;
    }
}
