package duke;

/**
 * Represents a base class for commands in the Duke application.
 * Subclasses of Command define specific actions that can be executed by the user.
 */
public abstract class Command {

    /**
     * Enumeration of possible command types.
     */
    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        DELETE,
        BYE
    }

    /**
     * Executes the command.
     *
     * @param taskList The TaskList object that stores the list of tasks.
     * @param ui       The Ui object responsible for user interface interactions.
     * @param storage  The Storage object responsible for reading and writing data to a file.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}

