package duke;

/**
 * Enumerates the various command types that the Duke application can recognize and process.
 * <p>
 * Each enumeration value corresponds to a specific command that the user can issue to interact with
 * the application.
 * </p>
 */
public enum CommandType {
    /** Command to exit the application. */
    BYE,

    /** Command to list all tasks. */
    LIST,

    /** Command to add a new 'ToDo' task. */
    TODO,

    /** Command to add a new 'Deadline' task. */
    DEADLINE,

    /** Command to add a new 'Event' task. */
    EVENT,

    /** Command to mark a task as done. */
    MARK,

    /** Command to unmark a previously marked task. */
    UNMARK,

    /** Command to delete a task. */
    DELETE,

    /** Command to list all tasks on a specified date. */
    TASKS_ON_DATE,

    /** Command type when the input command is not recognized. */
    UNKNOWN
}
