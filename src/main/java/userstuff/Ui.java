package userstuff;

import java.util.Scanner;

/**
 * A class to handle the ui.Ui of chatbot.
 */
public class Ui {
    /** The Scanner object used by this ui to read input. */
    Scanner scanner;

    /**
     * Initialises the scanner field variable.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Draws a line separating each conversation.
     *
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Greets the user of chatbot.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("\tHello I am Vishnu.");
        System.out.println("\tWhat can I do for you?");
        this.showLine();
    }

    /**
     * Says bye to the user.
     */
    public void showBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Shows the given message to the user.
     * @param s The string to show to the uesr.
     */
    public void showMessage(String s) {
        System.out.println("\t" + s);
    }


    public String readCommand() {
        return this.scanner.nextLine();
    }

}
