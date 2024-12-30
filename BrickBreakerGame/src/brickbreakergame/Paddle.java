
import java.awt.Rectangle;

public class Paddle {
    private int x;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 12;
    private static final int MOVE_DISTANCE = 40;

    public Paddle(int startX) {
        this.x = startX;
    }

    public void moveLeft() {
        if (x > 0) x -= MOVE_DISTANCE;
    }

    public void moveRight() {
        if (x < 692 - WIDTH) x += MOVE_DISTANCE;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, 540, WIDTH, HEIGHT);
    }

    public int getX() { return x; }
    public static int getWidth() { return WIDTH; }
    public static int getHeight() { return HEIGHT; }
}
