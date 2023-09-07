package duke.commands;

/**
 * The Command enum represents the possible commands that the Duke chatbot can handle.
 * These commands correspond to user inputs.
 */
public enum Command {
    /**
     * Represents the "todo" command for adding a to-do task.
     */
    TODO,

    /**
     * Represents the "deadline" command for adding a task with a deadline.
     */
    DEADLINE,

    /**
     * Represents the "event" command for adding an event task.
     */
    EVENT,

    /**
     * Represents the "list" command for listing all tasks.
     */
    LIST,

    /**
     * Represents the "mark" command for marking a task as done.
     */
    MARK,

    /**
     * Represents the "unmark" command for marking a task as undone.
     */
    UNMARK,

    /**
     * Represents the "delete" command for deleting a task.
     */
    DELETE,

    /**
     * Represents the "find" command for searching for tasks.
     */
    FIND,

    /**
     * Represents the "bye" command for exiting the chatbot.
     */
    BYE
}
