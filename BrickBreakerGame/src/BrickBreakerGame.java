import javax.swing.*;

public class BrickBreakerGame extends JFrame {
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 600;

    public BrickBreakerGame() {
        setTitle("Brick Breaker Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        this.getContentPane().add(gamePanel); // Ensure we're adding the panel to the content pane
        gamePanel.requestFocusInWindow();    // Allow the panel to capture key events
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BrickBreakerGame game = new BrickBreakerGame();
            game.setVisible(true); // Display the game window
        });
    }
}
