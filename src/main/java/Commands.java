/**
 * use enums to store all the possible commands
 */
public enum Commands {
    /**
     * command to list out all current tasks
     */
    LIST,
    /**
     * command to mark a task as completed, requires a index param.
     */
    MARK,
    /**
     * command to mark a task as not completed, requires a index param.
     */
    UNMARK,
    /**
     * command to create a todo task, no flags required
     */
    TODO,
    /**
     * command to create a event task, requires /from and /to flags
     */
    EVENT,
    /**
     * command to create a event task, requires the /by flag
     */
    DEADLINE,
    /**
     * command to delete a task, requires a index param.
     */
    DELETE,
    /**
     * command to denote an unrecognised input
     */
    UNRECOGNISED
}