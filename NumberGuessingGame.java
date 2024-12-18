import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0; // To keep track of the user's score
        boolean playAgain = true; // Flag to control multiple rounds

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int number = random.nextInt(100) + 1; // Generate random number between 1 and 100
            int attemptsLeft = 5; // Limit the number of attempts per round
            boolean guessedCorrectly = false;

            System.out.println("\nI have generated a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it!");

            // Loop for the guessing attempts
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess;

                // Validate user input
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                if (guess < 1 || guess > 100) {
                    System.out.println("Please guess a number between 1 and 100.");
                    continue;
                }

                // Compare the guess with the generated number
                if (guess == number) {
                    System.out.println("Congratulations! You've guessed the correct number!");
                    guessedCorrectly = true;
                    score++; // Increase score for correct guess
                    break;
                } else if (guess < number) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                attemptsLeft--; // Reduce remaining attempts
                if (attemptsLeft > 0) {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                }
            }

            // Provide feedback if the user fails to guess the number
            if (!guessedCorrectly) {
                System.out.println("You've run out of attempts. The correct number was: " + number);
            }

            // Display the current score
            System.out.println("Your current score: " + score);

            // Ask if the user wants to play another round
            System.out.print("Would you like to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");
        }

        // End of game message
        System.out.println("Thank you for playing! Your final score: " + score);
        scanner.close();
    }
}