package smolbrain;

import java.util.Scanner;

/**
 * Deals with user interaction and display of messages.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a straight line to the user.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next line that is entered by the user.
     *
     * @return String input from user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a formatted error message from the exception passed.
     *
     * @param e Exception encountered by program.
     */
    public void showError(Exception e) {
        System.out.println("☹ OOPS!!! " + e);
    }

    /**
     * Displays the specified message to the user.
     *
     * @param s Message to display.
     */
    public void showMessage(String s) {
        System.out.println(s);
    }

}
