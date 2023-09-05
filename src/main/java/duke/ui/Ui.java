package duke.ui;

import java.util.Scanner;

/**
 * Represents the UI for user interaction.
 */
public class Ui {
    /** Horizontal line and Scanner used to scan user input. */
    private static final String HORIZONTAL_LINE = "----------------------------(≧▽≦)----------------------------";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line as defined beforehand.
     */
    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints message wrapped between horizontal lines.
     *
     * @param msg Message to be printed.
     */
    public void showMessage(String msg) {
        showHorizontalLine();
        System.out.println(msg);
        showHorizontalLine();
    }

    /**
     * Prints a welcome message.
     */
    public void helloGreeting() {
        showMessage("Hello! I'm ForsakenX\nWhat can I do for you?");
    }

    /**
     * Prints a farewell message.
     */
    public void byeGreeting() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        showMessage(" ☹ Loading error! File may be corrupted.");
    }

    /**
     * Prints an error message.
     *
     * @param errorMsg Error message to be printed.
     */
    public void showError(String errorMsg) {
        showMessage(errorMsg);
    }

    /**
     * Reads user input.
     *
     * @return Scanned user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
