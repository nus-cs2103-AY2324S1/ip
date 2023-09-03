package duke.exception;

/**
 * Represents an InvalidCommandException that is thrown when an invalid command is entered by the user.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs a new InvalidCommandException with a default error message and the list of available commands.
     */
    public InvalidCommandException() {
        super("Invalid Command.\n"
                + "Below are the list of available commands:\n"
                + "list - displays the list of tasks\n"
                + "mark x - marks task x as done\n"
                + "unmark x - marks task x as undone\n"
                + "delete x - deletes task x\n"
                + "todo x - creates task with description x\n"
                + "deadline x /by y - creates task with description x by deadline y\n"
                + "event x /from y /to z - creates task with description x from y to z");
    }
}