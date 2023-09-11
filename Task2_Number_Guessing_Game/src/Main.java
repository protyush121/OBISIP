import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class NumberGuessingGameSwing extends JFrame {
    private final int minRange = 1;
    private final int maxRange = 100;
    private final int totalRounds = 3;
    private final int totalAttempts = 7;

    private int currentRound = 1;
    private int currentAttempts = totalAttempts;
    private int totalScore = 0;
    private int targetNumber;

    private JLabel infoLabel;
    private JTextField guessField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;

    public NumberGuessingGameSwing() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        setResizable(false);

        infoLabel = new JLabel("Round " + currentRound + " of " + totalRounds);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(infoLabel);

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);
        add(guessField);

        submitButton = new JButton("Submit Guess");
        add(submitButton);

        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        add(resultLabel);

        scoreLabel = new JLabel("Total Score: " + totalScore);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(scoreLabel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentAttempts > 0) {
                    int userGuess = Integer.parseInt(guessField.getText());
                    currentAttempts--;

                    if (userGuess < targetNumber) {
                        resultLabel.setText("Higher! Attempts left: " + currentAttempts);
                    } else if (userGuess > targetNumber) {
                        resultLabel.setText("Lower! Attempts left: " + currentAttempts);
                    } else {
                        int roundScore = currentAttempts + 1;
                        totalScore += roundScore;
                        scoreLabel.setText("Total Score: " + totalScore);
                        resultLabel.setText("Congratulations! You've guessed the number " + targetNumber + " with " + (currentAttempts + 1) + " attempts remaining.");

                        if (currentRound < totalRounds) {
                            currentRound++;
                            currentAttempts = totalAttempts;
                            targetNumber = generateRandomNumber();
                            infoLabel.setText("Round " + currentRound + " of " + totalRounds);
                            guessField.setText("");
                            resultLabel.setText("");
                        } else {
                            infoLabel.setText("Game Over!");
                            guessField.setEnabled(false);
                            submitButton.setEnabled(false);
                        }
                    }
                } else {
                    resultLabel.setText("Sorry, you've used all your attempts. The number was: " + targetNumber);
                }
            }
        });

        targetNumber = generateRandomNumber();
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(maxRange - minRange + 1) + minRange;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGameSwing game = new NumberGuessingGameSwing();
                game.setSize(300, 250);
                game.setVisible(true);
            }
        });
    }
}
