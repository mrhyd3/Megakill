package hyde.megakill.core;

public class Hud {
    private int score = 0;
    private boolean endGame = false;

    public Hud() {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void render() {
        if (endGame)
            Font.drawText("GAME OVER!", GlobalValues.screenWidth / 2, GlobalValues.screenHeight / 2);
        Font.drawText("score: " + score, 20, 20);
    }

    public void endGame() {
        endGame = true;
    }
}
