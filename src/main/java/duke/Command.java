package duke;

/**
 * The `Command` class defines constants representing various commands that can be used in the Duke application.
 * These commands are used for user interaction.
 */
public class Command {

    /**
     * Represents the "exit" command, which allows the user to exit the Duke application.
     */
    public static final String EXIT = "bye";

    /**
     * Represents the "list" command, which allows the user to list all tasks.
     */
    public static final String LIST = "list";

    /**
     * Represents the "list week" command, which allows the user to list tasks due within a week.
     */
    public static final String LIST_WITHIN_WEEK = "list week";

    /**
     * Represents the "list month" command, which allows the user to list tasks due within a month.
     */
    public static final String LIST_WITHIN_MONTH = "list month";

    /**
     * Represents the "delete" command, which allows the user to delete a task.
     * The task number should be appended to this command.
     * Example: "delete 2" will delete the task with index 2.
     */
    public static final String DELETE = "delete ";

    /**
     * Represents the "mark" command, which allows the user to mark a task as done.
     * The task number should be appended to this command.
     * Example: "mark 1" will mark the task with index 1 as done.
     */
    public static final String MARK = "mark ";

    /**
     * Represents the "unmark" command, which allows the user to mark a task as not done.
     * The task number should be appended to this command.
     * Example: "unmark 3" will mark the task with index 3 as not done.
     */
    public static final String UNMARK = "unmark ";
}
