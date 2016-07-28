package reversi.reversi;

/**
 * Created by toutoune134 on 28/07/2016.
 */
public class Coordinates {

    private int x;
    private int y;

    public  Coordinates(int a, int b) {
        x = a;
        y = b;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
