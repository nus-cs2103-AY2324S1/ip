package duke;

/**
 * The TaskType enum represents different types of tasks in the Duke application.
 * It includes TODO, DEADLINE, and EVENT as possible task types.
 */
public enum TaskType {
    /**
     * Represents a TODO task type.
     */
    TODO,

    /**
     * Represents a DEADLINE task type.
     */
    DEADLINE,

    /**
     * Represents an EVENT task type.
     */
    EVENT;

    /**
     * Default constructor for the TaskType enum.
     * This constructor is private as the enum constants do not require initialization.
     */
    private TaskType() {
    }
}
