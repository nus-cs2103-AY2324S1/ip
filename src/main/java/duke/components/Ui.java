package duke.components;

import duke.exceptions.DukeException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Class handling interactions with users
 */
public class Ui {
    static final String LOGO = "        _______ _                _____ \n" +
            "     /\\|__   __| |        /\\    / ____|\n" +
            "    /  \\  | |  | |       /  \\  | (___  \n" +
            "   / /\\ \\ | |  | |      / /\\ \\  \\___ \\ \n" +
            "  / ____ \\| |  | |____ / ____ \\ ____) |\n" +
            " /_/    \\_\\_|  |______/_/    \\_\\_____/ \n";
    // Chatbot name
    static final String NAME = "Atlas";
    final BufferedReader inputReader;

    /**
     * Constructs a Ui object
     */
    public Ui() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints a horizontal line containing the character '-' of width 80.
     */
    public void printLine() {
        final int consoleWidth = 80;
        final String line = "_".repeat(consoleWidth);
        System.out.println(line);
    }

    /**
     * Prints error message to screen.
     * @param message Error message to print.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints greeting to console.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        printLine();
        System.out.printf("Hello, I'm %s!\n", NAME);
        System.out.println("What would you like me to do today?");
        printLine();
    }

    /**
     * Reads user input from command line
     * @return String containing user input
     * @throws DukeException Exception thrown if I/O error encountered
     */
    public String readCommand() throws DukeException {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            throw new DukeException("I/O error encountered: " + e.getMessage());
        }
    }

    /**
     * Prints string to screen
     * @param s String to be printed
     */
    public void printToScreen(String s) {
        System.out.println(s);
    }
}
