package hyde.megakill.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Missile extends Shape {
    private float length = 6.25f;

    public Missile(float x, float y, int angle, double speed) {
        this.pos.x = x;
        this.pos.y = y;
        this.angle = angle;
        this.speed = 1.0f + speed;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        ifOnEdgeMoveToOppositeEdge();
        calc(true);

        pos.x += xForwardMomentum;
        pos.y += yForwardMomentum;

        shapeRenderer.identity();
        shapeRenderer.translate(pos.x, pos.y, 0);
        shapeRenderer.rotate(0, 0, 1, angle);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Triangle);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.triangle(0,0,length,0,length/2,length);
        shapeRenderer.end();
    }
}
