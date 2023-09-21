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

    /**
     * Represents the "find" command, which allows the user to find tasks based on keywords.
     * The search query strings should be appended to this command.
     * Example: "find game" will find tasks that match string game.
     */
    public static final String FIND = "find ";

    /**
     * Represents the "tag" command, which allows the user to add tags to a task.
     * The task number and tags should be appended to this command.
     * Example: "tag 3 fun sport" will add tags fun and sport to task 3.
     */
    public static final String TAG = "tag ";

    /**
     * Represents the "doafter" command, which allows the user to add parent task to a task.
     * The task numbers of child and parent respectively to be added to command.
     * Example: "doafter 3 4" will set 4 to be parent of 3.
     */
    public static final String DOAFTER = "doafter ";

    /**
     * Represents the "start" system command, which allows the ui to intialize
     * the starting message. If by chance used by user, it will just display the greeting.
     */
    public static final String START = "SystemCode247890Start";
}
