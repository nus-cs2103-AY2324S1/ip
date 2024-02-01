package duke;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "_____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns a user command from the standard input.
     *
     * @return A string representing the user's input command.
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    /**
     * Displays a greeting message to the user.
     */
    public static String greet() {
        StringBuilder sb = new StringBuilder();
        String logo = "       .__\n"
                + "  ____ |__| ____   ____\n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/\n"
                + "     \\/        \\/";
        sb.append("Hello from\n" + logo + "\n");
        sb.append("Hello! I'm NINO!" + "\n");
        sb.append("What can I do for you?" + "\n");
        return sb.toString();
    }

    /**
     * Displays an error message to the user.
     *
     * @param e A DukeException representing the error to be displayed.
     */
    public static String showError(DukeException e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage() + "\n");
        return sb.toString();
    }
}
