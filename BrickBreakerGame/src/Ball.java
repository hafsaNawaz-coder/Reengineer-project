
import java.awt.Rectangle;

public class Ball {
    private int x, y, xDir, yDir;
    private static final int SIZE = 20;

    public Ball(int startX, int startY, int xDir, int yDir) {
        this.x = startX;
        this.y = startY;
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public void move() {
        x += xDir;
        y += yDir;
    }

    public void bounceX() {
        xDir = -xDir;
    }

    public void bounceY() {
        yDir = -yDir;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public static int getSize() { return SIZE; }
}
