import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GuessingGame extends JFrame {
    private final Font mainFont = new Font("Segoe Print", Font.BOLD, 18);
    private JTextField guessField;
    private JLabel resultLabel;
    private int numberToGuess;
    private int attemptsLeft;

    public GuessingGame() {
        initialize();
    }

    private void initialize() {
        // Generate a random number to guess
        numberToGuess = new Random().nextInt(100) + 1;
        attemptsLeft = 6;

        // Create UI components
        JLabel guessLabel = new JLabel("Guess a number between 1 and 100:");
        guessLabel.setFont(mainFont);

        guessField = new JTextField(10);
        guessField.setFont(mainFont);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());

        resultLabel = new JLabel("");
        resultLabel.setFont(mainFont);

        // Create panel and add components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(guessLabel, BorderLayout.NORTH);
        mainPanel.add(guessField, BorderLayout.CENTER);
        mainPanel.add(guessButton, BorderLayout.EAST);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        // Frame settings
        setTitle("Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guess = guessField.getText();
            int guessInt = Integer.parseInt(guess);

            if (guessInt < numberToGuess) {
                resultLabel.setText("Too low! Attempts left: " + attemptsLeft);
            } else if (guessInt > numberToGuess) {
                resultLabel.setText("Too high! Attempts left: " + attemptsLeft);
            } else {
                resultLabel.setText(" Congratulations! You won!");
                guessField.setEnabled(false);
            }

            attemptsLeft--;
            if (attemptsLeft == 0) {
                resultLabel.setText("Game over! The number was " + numberToGuess);
                guessField.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}