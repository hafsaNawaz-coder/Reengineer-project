import java.awt.*;

public class BrickMap {
    private int[][] bricks;
    private int rows, columns;
    private final int brickWidth = 80;
    private final int brickHeight = 30;

    public BrickMap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        bricks = new int[rows][columns];
        initializeBricks();
    }

    private void initializeBricks() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                bricks[i][j] = 1; // All bricks are initially present
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (bricks[i][j] > 0) {
                    // Draw a brick
                    int x = j * brickWidth + 80;
                    int y = i * brickHeight + 50;
                    g.setColor(new Color(204, 102, 51)); // Reddish-brown
                    g.fillRoundRect(x, y, brickWidth, brickHeight, 10, 10);
                    g.setColor(Color.BLACK); // Outline for better visibility
                    g.drawRoundRect(x, y, brickWidth, brickHeight, 10, 10);
                }
            }
        }
    }

    public int getBrickValue(int row, int col) {
        return bricks[row][col];
    }

    public void setBrickValue(int value, int row, int col) {
        bricks[row][col] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }
}