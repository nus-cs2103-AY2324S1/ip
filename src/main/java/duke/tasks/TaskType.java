package duke.tasks;

/**
 * Represents different types of tasks.
 */
public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    /** The string representation of the command. */
    private final String type;

    /**
     * The constructor is used to create a new command with the given value.
     *
     * @param value The string representation of the command.
     */
    private TaskType(String type) {
        this.type = type;
    }
}
