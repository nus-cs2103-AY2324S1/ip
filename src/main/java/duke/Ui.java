package duke;

import duke.task.Task;

/**
 * Handles the interaction with user
 */
public class Ui {

    /**
     * Default Exit Message
     */
    public static final String GOODBYE_MESSAGE = "Quack Quack! Quack hopes to see you again soon!\n";

    /**
     * Default Welcome Message
     */
    public static final String WELCOME_MESSAGE = "Quack Quack! I am a duck named Quack\n"
            + "Quack will remember the task you give quack!\n\n"
            + Ui.HELP_MESSAGE;

    /**
     * Default Help Message
     */
    private static final String HELP_MESSAGE = "Quack understands these commands: list, mark, "
            + "unmark, delete, todo, deadline, event\n\n"
            + "For mark/unmark/delete please provide a number after, such as mark 2.\n\n"
            + "deadline requires the /by keyword and event requires the /from and /to keyword.\n\n"
            + "Please provide a valid date and time after the keyword with the following format:"
            + " YYYY-MM-DD HH:MM\n";

    /**
     * Constructs a new UI instance
     */
    public Ui() {
    }

    /**
     * gets the default help message
     *
     * @return the default help message
     */
    public String getHelpMessage() {
        return Ui.HELP_MESSAGE;
    }

    /**
     * Prints Error message in the quack convention
     *
     * @param e - the error message
     * @return the formatted error message
     */
    public String getErrorMessage(String e) {
        return "QUACK QUACK!! " + e;
    }

    /**
     * Prints Unexpected Error message in the quack convention
     *
     * @param e - the error message
     * @return the formatted error message
     */
    public String getUnexpectedErrorMessage(String e) {
        return "QUACK QUACK!! unexpected error: " + e;
    }

    /**
     * Generate the usually used representation when adding tasks
     *
     * @param newTask - the task being added
     * @param length  - the current number of tasks
     * @return the formatted string
     */
    public String getNewTaskMessage(Task newTask, int length) {
        return "Quack! I have added this task:\n"
                + newTask.toString()
                + "\nQuack! Quack is currently remembering "
                + length
                + " tasks.";
    }
}
