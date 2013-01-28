package hyde.megakill.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import hyde.megakill.core.GlobalValues;

public abstract class Shape {
    protected Vector2 pos = new Vector2();

    public abstract void render(ShapeRenderer shapeRenderer);

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
}

