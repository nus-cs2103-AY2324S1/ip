package smolbrain;

import java.util.Scanner;

/**
 * Deals with user interaction and display of messages.
 */
public class Ui {

    private Scanner sc;
    private MainWindow mainwindow;

    /**
     * Creates a Ui object.
     *
     * @param mainwindow MainWindow object used for this application.
     */
    public Ui(MainWindow mainwindow) {
        sc = new Scanner(System.in);
        assert mainwindow != null : "No mainwindow was found";
        this.mainwindow = mainwindow;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        mainwindow.displayBotText("Hello! I'm Smolbrain\nWhat can I do for you?");
    }

    /**
     * Displays a straight line to the user.
     */
    public void showLine() {
        mainwindow.displayBotText("____________________________________________________________");
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
        mainwindow.displayBotText("OOPS!!! " + e);
    }

    /**
     * Displays the specified message to the user.
     *
     * @param s Message to display.
     */
    public void showMessage(String s) {
        mainwindow.displayBotText(s);
    }

}
