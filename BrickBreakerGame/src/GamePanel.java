import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GamePanel extends JPanel implements KeyListener, ActionListener {
    private static final int PADDLE_WIDTH = 120;
    private static final int PADDLE_HEIGHT = 12;
    private static final int BALL_SIZE = 20;
    
    private int paddleX = 310;
    private int ballX = 120;
    private int ballY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private int score = 0;
    private int totalBricks = 0;
    private boolean isPlaying = true;  // To track the game's pause state
    private Timer timer;
    private BrickMap brickMap;

    public GamePanel() {
        brickMap = new BrickMap(5, 7); // Set number of rows and columns
        totalBricks = brickMap.getRows() * brickMap.getColumns();
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(8, this); // Timer to refresh the game at regular intervals
        timer.start();
    }
    @Override
    public void paint(Graphics g) {
        // Background color and drawing
         g.setColor(new Color(255, 255, 204)); // Set background color to light yellow
    g.fillRect(0, 0, getWidth(), getHeight());

        // Draw walls
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 3, 592); // Left wall
        g.fillRect(0, 0, 692, 3); // Top wall
        g.fillRect(691, 0, 3, 592); // Right wall
        
        // Draw the bricks
        brickMap.draw(g);

        // Draw the paddle
        g.setColor(new Color(169, 169, 169)); // Grey color for paddle
        g.fillRoundRect(paddleX, 530, PADDLE_WIDTH, PADDLE_HEIGHT, 20, 20); // Rounded corners for paddle
        g.setColor(Color.DARK_GRAY);
        g.drawRoundRect(paddleX, 530, PADDLE_WIDTH, PADDLE_HEIGHT, 20, 20); // Dark outline for paddle

        // Draw the ball (red color)
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // Draw the score with improved font
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 30));
        g.drawString("Score: " + score, 520, 30);
        // Draw game over message or winning message
        if (totalBricks == 0) {
            isPlaying = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(new Color(0, 255, 0));
            g.setFont(new Font("Verdana", Font.BOLD, 40));
            g.drawString("You Won!", 230, 300);
            g.setFont(new Font("Verdana", Font.PLAIN, 30));
            g.drawString("Your Score: " + score, 230, 340);
            g.setFont(new Font("Verdana", Font.PLAIN, 25));
            g.drawString("Press Enter to Restart.", 230, 380);
        } else if (ballY > 570) {
            isPlaying = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("Verdana", Font.BOLD, 40));
            g.drawString("Game Over", 230, 300);
            g.setFont(new Font("Verdana", Font.PLAIN, 30));
            g.drawString("Your Score: " + score, 230, 340);
            g.setFont(new Font("Verdana", Font.PLAIN, 25));
            g.drawString("Press Enter to Restart.", 230, 380);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPlaying) {
            checkBallCollision();
            moveBall();
        }
        repaint();
    }
    private void checkBallCollision() {
        // Paddle collision
        if (new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE).intersects(new Rectangle(paddleX, 540, PADDLE_WIDTH, PADDLE_HEIGHT))) {
            ballYDir = -ballYDir;
        }

        // Brick collision
        for (int i = 0; i < brickMap.getRows(); i++) {
            for (int j = 0; j < brickMap.getColumns(); j++) {
                if (brickMap.getBrickValue(i, j) > 0) {
                    int brickX = j * brickMap.getBrickWidth() + 80;
                    int brickY = i * brickMap.getBrickHeight() + 50;
                    Rectangle brickRect = new Rectangle(brickX, brickY, brickMap.getBrickWidth(), brickMap.getBrickHeight());
                    Rectangle ballRect = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);
                    
                    if (ballRect.intersects(brickRect)) {
                        brickMap.setBrickValue(0, i, j);  // Mark brick as broken
                        totalBricks--;
                        score += 5;

                        // Ball direction change after brick collision
                        if (ballX + BALL_SIZE - 1 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
                            ballXDir = -ballXDir;
                        } else {
                            ballYDir = -ballYDir;
                        }
                    }
                }
            }
        }
    }

    private void moveBall() {
        ballX += ballXDir;
        ballY += ballYDir;

        if (ballX < 0 || ballX > 670) {
            ballXDir = -ballXDir;
        }
        if (ballY < 0) {
            ballYDir = -ballYDir;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT && paddleX > 0) {
            paddleX -= 40;
        }
        if (key == KeyEvent.VK_RIGHT && paddleX < 600) {
            paddleX += 40;
        }
        if (key == KeyEvent.VK_SPACE) {
            togglePause();  // Toggle pause and resume
        }
        if (key == KeyEvent.VK_ENTER) {
            restartGame(); // Restart the game
        }
    }

    private void togglePause() {
        if (isPlaying) {
            isPlaying = false;
            timer.stop(); // Stop the timer (pauses the game)
        } else {
            isPlaying = true;
            timer.start(); // Restart the timer (resumes the game)
        }
    }
    private void restartGame() {
        totalBricks = brickMap.getRows() * brickMap.getColumns();
        score = 0;
        ballX = 120;
        ballY = 350;
        ballXDir = -1;
        ballYDir = -2;
        paddleX = 310;
        isPlaying = true;
        brickMap = new BrickMap(5, 7); // Reset bricks
        timer.start(); // Start the timer for the new game
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}