package hyde.megakill.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import hyde.megakill.shapes.Asteroid;
import hyde.megakill.shapes.Missile;
import hyde.megakill.shapes.PlayerShape;
import hyde.megakill.shapes.Shape;
import hyde.megakill.util.Random;

import java.util.ArrayList;

public class World {
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    ArrayList<Shape> asteroids = new ArrayList<Shape>();
    ArrayList<Shape> asteroidsToRemove = new ArrayList<Shape>();
    ArrayList<Shape> asteroidsToAdd = new ArrayList<Shape>();
    ArrayList<Shape> missilesToRemove = new ArrayList<Shape>();

    PlayerShape player = new PlayerShape();
    boolean gameOver = false;

    Hud hud = new Hud();

    public World() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true,GlobalValues.screenWidth,
                GlobalValues.screenHeight);
        shapeRenderer = new ShapeRenderer();

        for (int i=0; i<10; i++) {
            int size = (Random.getRandomInteger(4)+1)*5;
            Asteroid asteroid = new Asteroid(size);
            System.out.println(asteroid.toString());
            addShape(asteroid);
        }
    }

    public void render() {
        camera.update();
        checkCollisions();
        shapeRenderer.setProjectionMatrix(camera.combined);
        hud.render();
        if (!gameOver) {
            player.render(shapeRenderer);
            for ( Shape shape : asteroids)
                shape.render(shapeRenderer);
        } else {
            hud.endGame();
        }
    }

    private void checkCollisions() {
        for ( Shape asteroid : asteroids) {
            if ( collides(player.getPos().x, player.getPos().y, player.getSize() - 2,
                    asteroid.getPos().x, asteroid.getPos().y, asteroid.getSize()) ) {
                gameOver = true;
            }
        }
        boolean stopChecking = false;
        for (Missile missile : player.getMissiles() ) {
            for ( Shape asteroid : asteroids) {
                if ( collides(asteroid.getPos().x, asteroid.getPos().y, asteroid.getSize(),
                        missile.getPos().x, missile.getPos().y, 6) ) {
                    hud.setScore((int)(asteroid.getSize() * 10));

                    asteroidsToRemove.add(asteroid);
                    missilesToRemove.add(missile);
                    if (asteroid.getSize() > 5.0f) {
                        int numberOfNewAsteroids = (int)(asteroid.getSize() / 5);

                        for (int i=0; i<numberOfNewAsteroids; i++)
                            asteroidsToAdd.add(new Asteroid(asteroid.getSize() / numberOfNewAsteroids,
                                    asteroid.getPos().x, asteroid.getPos().y));
                    }
                    stopChecking = true;
                    break;
                }
            }
            if (stopChecking)
                break;
        }
        if (missilesToRemove.size() > 0) {
            player.getMissiles().removeAll(missilesToRemove);
            missilesToRemove.clear();
        }
        if ( asteroidsToRemove.size() > 0) {
            asteroids.removeAll(asteroidsToRemove);
            asteroidsToRemove.clear();
        }
        if ( asteroidsToAdd.size() > 0) {
            asteroids.addAll(asteroidsToAdd);
            asteroidsToAdd.clear();
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
