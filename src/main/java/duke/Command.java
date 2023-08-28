package duke;

/**
 * Represents the available commands.
 */
public enum Command {
    /**
     * Command to exit the application.
     */
    BYE,

    /**
     * Command to list all tasks.
     */
    LIST,

    /**
     * Command to mark a task done.
     */
    MARK,

    /**
     * Command to mark a task undone.
     */
    UNMARK,

    /**
     * Command to delete a task.
     */
    DELETE,

    /**
     * Command to add a new todo.
     */
    TODO,

    /**
     * Command to add a new deadline.
     */
    DEADLINE,

    /**
     * Command to add a new event.
     */
    EVENT,

    /**
     * Command to display help information.
     */
    HELP
}
