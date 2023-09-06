package functional;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * The Ui class handles user interaction and displays messages to the user.
 * It provides methods for input and output.
 */
public class Ui {
    String[] currentLine;
    String input;

    /**
     * Constructs an Ui instance with a reference to the String input
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
    public void showWelcome() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Husky\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() throws functional.DukeException {
        Scanner io = new Scanner(System.in);
        try {
            String s = io.nextLine();
            this.currentLine = s.split(" ");
            this.input = s;
            return s;
        } catch (NoSuchElementException e) {
            throw new functional.DukeException();
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
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * prints a message indicating that no file was previously saved
     */
    public void showLoadingError() {
        System.out.println("No preexisting file\n" + "creating new file");
    }
}
