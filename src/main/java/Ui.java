import Exceptions.DukeException;

/**
 * Represents the User Interface of the Duke App.
 * Each <Code>Ui</Code> method displays a message to the user.
 */
public class Ui {

    /**
     * Shows the welcome message when the user first opens the app.
     * @return The welcome message.
     */
    public String showWelcome() {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        return "Hello!\n" + logo;
    }

    /**
     * Shows the Duke Error as a message.
     * @param e the DukeException thrown.
     * @return The custom Duke Error message.
     */
    public String showDukeError(DukeException e) {
        return ":( Chewy can't understand! " + e.getMessage();
    }

    /**
     * Shows the list of commands that the user can type.
     * @return The help message.
     */
    public String displayHelpMessage() {
        return "List of available commands:\n"
            + " - todo <description>: Add a new todo task\n"
            + " - deadline <description> /by <date>: Add a new deadline task\n"
            + " - event <description> /from <start> /to <end>: Add a new event task with start and end time\n"
            + " - list: Display the list of tasks\n"
            + " - mark <taskNumber>: Mark a task as done\n"
            + " - unmark <taskNumber>: Unmark a task as done\n"
            + " - bye: Exit Chewy\n";
        // Add more commands in the future
    }

    /**
     * Shows the farewell message when the user types 'bye'.
     * @return The farewell message.
     */
    public String displayFarewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows any other exception that is caught by the program.
     * @param e The Exception thrown.
     * @return a message that shows the exception.
     */
    public String showException(Exception e) {
        return "Something unexpected happened. try 'help' to see a list of commands";
    }
}
