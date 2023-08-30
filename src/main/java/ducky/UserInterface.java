package ducky;

import java.util.Scanner;

/**
 * Represents the user interface of a Ducky chatbot instance.
 */
public class UserInterface {
    private final Scanner sc;

    /**
     * Creates a user interface with the specified Scanner instance.
     * @param sc Scanner to read input from.
     */
    public UserInterface(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Reads and returns input from scanner.
     * @return Input from scanner.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Ducky and I'm here to help!");
    }

    /**
     * Prints a farewell message.
     */
    public void showFarewell() {
        System.out.println("Bye! See you again!");
    }

    /**
     * Prints specified error message.
     * @param message Error message to be printed.
     */
    public void showError(String message) {
        System.out.println("Error!");
        System.out.println(message);
    }

    /**
     * Prints a horizontal line divider.
     */
    public void showLine() {
        System.out.println("____________");
    }

    /**
     * Prints specified arguments line by line.
     * @param args Arguments to be printed.
     */
    public void showMessagePerLine(String... args) {
        for (String a : args) {
            System.out.println(a);
        }
    }
}