import java.util.Scanner;
import java.util.concurrent.*;

class Question {
    String questionText;
    String[] options;
    int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean isCorrect(int userOption) {
        return userOption == correctOption;
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        // Create an array of quiz questions
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
            new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2),
            new Question("Who wrote 'Romeo and Juliet'?", new String[]{"1. William Wordsworth", "2. Charles Dickens", "3. William Shakespeare", "4. Mark Twain"}, 3),
            new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic", "2. Indian", "3. Arctic", "4. Pacific"}, 4),
            new Question("What is the chemical symbol for water?", new String[]{"1. H2O", "2. CO2", "3. O2", "4. N2"}, 1)
        };

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have 10 seconds to answer each question. Let's begin!");

        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            System.out.println("\nQuestion " + (i + 1) + ": " + q.questionText);

            for (String option : q.options) {
                System.out.println(option);
            }

            // Start a timer and get the user's answer
            int userAnswer = getUserAnswerWithTimeout(scanner, 10);

            if (userAnswer == -1) {
                System.out.println("Time's up! No answer submitted.");
            } else if (q.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was option " + q.correctOption + ".");
            }
        }

        // Display the final result
        System.out.println("\n=== Quiz Completed ===");
        System.out.println("Your final score: " + score + " out of " + questions.length);
        System.out.println("Correct answers: " + score);
        System.out.println("Incorrect answers: " + (questions.length - score));
        scanner.close();
    }

    // Method to get user input with a timeout
    public static int getUserAnswerWithTimeout(Scanner scanner, int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            System.out.print("Enter your answer (1-4): ");
            return scanner.nextInt();
        });

        try {
            return future.get(seconds, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true); // Cancel the task if it times out
            return -1; // Return -1 to indicate timeout
        } catch (Exception e) {
            return -1; // Handle other exceptions (e.g., invalid input)
        } finally {
            executor.shutdown();
        }
    }
}
