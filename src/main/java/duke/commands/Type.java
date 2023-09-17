package duke.commands;

/**
 * Enum representing various command types within the application.
 */
public enum Type {
    /** Command to exit the application. */
    BYE("bye", "Exit the application"),

    /** Command to add a task with a deadline. */
    DEADLINE("deadline [description] /by [date time]", "Include a deadline task and specify the deadline. Refer to \"help time\" for date formats"),

    /** Command to remove a task based on its index. */
    DELETE("delete [taskIndex]", "Remove the task with the given index"),

    /** Command to add an event task with start and end times. */
    EVENT("event [description] /by [date time] /from [date time]", "Add an event task specifying start and end times. Check \"help time\" for date formats"),

    /** Command to find and display events matching a keyword. */
    FIND("find [keyword]", "Show all events matching the keyword. Search is case-insensitive."),

    /** Command to display the help menu. */
    HELP("help", "Display the help menu"),

    /** Command to list all current tasks. */
    LIST("list", "Show all current tasks in the list"),

    /** Command to mark a task as completed based on its index. */
    MARK("mark [taskIndex]", "Set the task with the given index as completed"),

    /** Command to add a simple todo task. */
    TODO("todo [description]", "Include a todo task"),

    /** Command to unmark a task as completed based on its index. */
    UNMARK("unmark [taskIndex]", "Set the task with the given index as not done");

    /** The actual command string that users input. */
    private final String command;

    /** Description or details about what the command does. */
    private final String description;

    /**
     * Constructor to initialize each command type with its string representation and description.
     *
     * @param command     The actual command string.
     * @param description Description of the command.
     */
    Type(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Retrieves the command string.
     *
     * @return The command string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Retrieves the command description.
     *
     * @return The command description.
     */
    public String getDescription() {
        return description;
    }
}

