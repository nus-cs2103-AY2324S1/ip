package potato;

import java.util.Scanner;

/**
 * The Ui class handles user interface interactions in the Potato application.
 * It provides methods for displaying messages to the user and obtaining user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read user input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String showGoodbye() {
        return "You're cancelled. Leave.";
    }

    public void showLoadingError() {
        System.out.println("Oops there's nothing to load! Let's start a new list!");
    }
}
