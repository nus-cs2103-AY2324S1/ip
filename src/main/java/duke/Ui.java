package duke;

import java.util.Scanner;

/**
 * Handles the interaction with user
 */
public class Ui {

    /**
     * Default tab spacing
     */
    private static final String TAB = "     ";
    /**
     * Default Help Message
     */
    private static final String HELP_MESSAGE = "Quack understands these commands: list, mark, "
            + "unmark, delete, todo, deadline, event\n"
            + TAB + "For mark/unmark/delete please provide a number after, like such mark 2\n"
            + TAB + "deadline requires the /by keyword and event requires the /from and /to keyword\n"
            + TAB + "Please provide a valid date and time after the keyword with the following format:"
            + " YYYY-MM-DD HH:MM\n";
    /**
     * Default Welcome Message
     */
    private static final String WELCOME_MESSAGE = TAB + "Quack Quack! I am a duck named Quack\n"
            + TAB + "Quack will remember the task you give quack!\n"
            + TAB + Ui.HELP_MESSAGE;
    /**
     * Default Exit Message
     */
    private static final String GOODBYE_MESSAGE = TAB + "Quack Quack! Quack hopes to see you again soon!\n";
    /**
     * Line Break
     */
    private static final String LINE_BREAK = "    ____________________________________________________________\n";
    /**
     * App LOGO
     */
    private static final String LOGO = "\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████░░░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░████░░██████████░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░████░░██▒▒▒▒▒▒██░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░██░░██░░░░░░░░██░░░░░░░░░░░░░░██▒▒▒▒▒▒██░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░██░░░░██░░░░░░██░░░░░░░░░░░░░░████████░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░██░░░░░░██░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░████████████░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░████████████████░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░";
    /**
     * scanner instance, default to scan from System.in
     */
    private final Scanner scanner;

    /**
     * Constructs a new UI instance
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Close all relevant resources
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Reads a line of command to be parsed
     *
     * @return the line of command read
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the string to standard out with some modification
     *
     * @param string - the string you wish to print
     */
    public void println(String string) {
        if (string.startsWith(Ui.LINE_BREAK)) {
            System.out.println(string);
            return;
        }
        System.out.println(Ui.TAB + string);
    }

    /**
     * Prints the default welcome message
     */
    public void welcomeMessage() {
        this.println(Ui.LOGO);
        this.println(Ui.LINE_BREAK + Ui.WELCOME_MESSAGE);
        System.out.println();
        this.println("You can use the help command should be confused!!");
        this.println(Ui.LINE_BREAK);
    }

    /**
     * Prints the default help message
     */
    public void helpMessage() {
        this.println(Ui.HELP_MESSAGE);
    }

    /**
     * Prints the default goodbye message
     */
    public void goodbyeMessage() {
        this.println(Ui.LINE_BREAK + Ui.GOODBYE_MESSAGE + Ui.LINE_BREAK);
    }

    /**
     * Prints the default line spacer
     */
    public void lineBreak() {
        this.println(Ui.LINE_BREAK);
    }

    /**
     * Prints Error message in the quack convention
     *
     * @param e - the error message
     */
    public void errorMessage(String e) {
        this.println("QUACK QUACK!! " + e);
    }

    /**
     * Prints Unexpected Error message in the quack convention
     *
     * @param e - the error message
     */
    public void unexpectedError(String e) {
        this.println("QUACK QUACK!! unexpected error: " + e);
    }

}
