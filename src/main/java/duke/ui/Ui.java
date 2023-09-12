package duke.ui;

import java.time.format.DateTimeFormatter;

/**
 * A class that contains all the constants used by the user interface.
 */
public class Ui {
    /** The date and time input format used for parsing user input. */
    public static final DateTimeFormatter DATE_FORMAT_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /** The date and time output format used for displaying dates and times in messages. */
    public static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    /** The scanner used to read user input. */
    public static final String MESSAGE_WELCOME = "Hello! I'm David\nWhat can I do for you?";

    /** The message displayed when the user exits the application. */
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
}
