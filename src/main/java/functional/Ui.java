package functional;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The Ui class handles user interaction and displays messages to the user.
 * It provides methods for input and output.
 */
public class Ui {
    private String[] currentLine;
    private String input;

    /**
     * Constructs an Ui instance with a reference to the String input
     *
     * @param input The string supplied by the user
     */
    public Ui(String input) {
        this.input = input;
        this.currentLine = input.split(" ");
    }

    public Ui() {
    }

    /**
     * Displays a welcome message
     */
    public static String showWelcome() {
        return "____________________________________________________________\n"
                + " Hello! I'm Husky\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand(String input) throws DukeException {
        try {
            this.currentLine = input.split(" ");
            this.input = input;
            return input;
        } catch (NoSuchElementException e) {
            throw new DukeException("");
        }
    }

    public String readCommand() throws DukeException {
        Scanner io = new Scanner(System.in);
        try {
            String s = io.nextLine();
            this.currentLine = s.split(" ");
            this.input = s;
            return s;
        } catch (NoSuchElementException e) {
            throw new DukeException("No Such Element Error");
        }
    }

    /**
     * @return A string as shown below
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Obtain the index-th word separated by space
     *
     * @param index
     * @return the desired word in the command as a string
     */
    public String get(int index) {
        return currentLine[index];
    }

    /**
     * @return the length of the command as an integer
     */
    public int length() {
        return currentLine.length;
    }

    /**
     * @return the user command as a String
     */
    public String getInput() {
        return this.input;
    }

    /**
     * prints the error message
     *
     * @param msg error message to be shown
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * prints a message indicating that no file was previously saved
     */
    public String showLoadingError() {
        return "No preexisting file\n" + "creating new file";
    }
}
