package hyde.megakill.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;

public abstract class Shape {
    protected Vector2 pos = new Vector2();
    protected int angle = 0;
    protected double speed = 0;
    protected double xForwardMomentum = 0;
    protected double yForwardMomentum = 0;
    protected int angleFromLastForwardMovement = 0;
    protected boolean isDestroyed = false;
    protected float size;

    public abstract void render(ShapeRenderer shapeRenderer);
    public void destroy() {
        isDestroyed = true;
    }

    protected void ifOnEdgeMoveToOppositeEdge() {
        if (pos.x > GlobalValues.screenWidth)
            pos.x = 0;
        if (pos.x < 0)
            pos.x = GlobalValues.screenWidth;
        if (pos.y > GlobalValues.screenHeight)
            pos.y = 0;
        if (pos.y < 0)
            pos.y = GlobalValues.screenHeight;
    }

    protected void calc(boolean changeAngle) {
        if (changeAngle) {
            xForwardMomentum = Math.cos(Math.toRadians(angle)) * speed;
            yForwardMomentum = Math.sin(Math.toRadians(angle)) * speed;
            angleFromLastForwardMovement = angle;
        } else {
            xForwardMomentum = Math.cos(Math.toRadians(angleFromLastForwardMovement)) * speed * Gdx.graphics.getDeltaTime();
            yForwardMomentum = Math.sin(Math.toRadians(angleFromLastForwardMovement)) * speed * Gdx.graphics.getDeltaTime();
        }
        pos.x += xForwardMomentum;
        pos.y += yForwardMomentum;
    }

    public Vector2 getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "pos=" + pos +
                ", angle=" + angle +
                ", speed=" + speed +
                ", xForwardMomentum=" + xForwardMomentum +
                ", yForwardMomentum=" + yForwardMomentum +
                ", angleFromLastForwardMovement=" + angleFromLastForwardMovement +
                '}';
    }

    public float getSize() {
        return size;
    }
}

