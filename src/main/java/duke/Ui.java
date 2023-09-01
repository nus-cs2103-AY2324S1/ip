package duke;
import duke.exception.DukeException;

import java.util.Scanner;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {
    final static String HORIZONTAL_LINE = "_____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner =  new Scanner(System.in);
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
     * Displays a horizontal line to separate messages.
     */
    public static void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a greeting message to the user.
     */
    public static void greet() {
        String logo = "       .__\n"
                + "  ____ |__| ____   ____\n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/\n"
                + "     \\/        \\/";
        System.out.println("Hello from\n" + logo);
        Ui.showLine();
        System.out.println("Hello! I'm NINO!");
        System.out.println("What can I do for you?");
        Ui.showLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param e A DukeException representing the error to be displayed.
     */
    public static void showError(DukeException e) {
        Ui.showLine();
        System.out.println(e.getMessage());
        Ui.showLine();

    }
}
