package sana;
import java.util.Scanner;

/**
 * Represents a text user interface for Sana chatbot.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs an ui object with the given scanner to take in user input.
     *
     * @param scanner takes in user input.
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints out welcome message.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("Hello I'm Sana!\nWhat can I do for you today?");
        this.showLine();
    }

    /**
     * Reads the next command by reading the user input in a new line.
     *
     * @return
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints out line that acts as commands' dividers.
     */
    public void showLine() {
        System.out.println("_______________________________________");
    }

    /**
     * Prints out error message.
     *
     * @param msg error message printed.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

}
