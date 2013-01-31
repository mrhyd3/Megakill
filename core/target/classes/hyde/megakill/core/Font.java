package hyde.megakill.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Font {
    private static BitmapFont bitmapFont;
    private static SpriteBatch spriteBatch;

    public Font() {
        //BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("Calibri.fnt"),Gdx.files.internal("Calibri.png"),false);
        //bitmapFont = new BitmapFont();
        //spriteBatch = new SpriteBatch();
    }
    public static void drawText(String text, float x, float y) {
        if ( bitmapFont == null) {
            bitmapFont = new BitmapFont();
            spriteBatch = new SpriteBatch();
        }
        spriteBatch.begin();
        bitmapFont.draw(spriteBatch,text,x,y);
        spriteBatch.end();
    }
}
