package duke;

/**
 * The `TaskStatus` enum represents the status of a task, which can be either done or not done.
 * It provides symbolic representations for task status and allows converting status to a string.
 */
enum TaskStatus {
    /**
     * Represents a task that is marked as done.
     */
    DONE("[X]"),

    /**
     * Represents a task that is not marked as done.
     */
    NOT_DONE("[ ]");


    private final String symbol;

    /**
     * Constructs a `TaskStatus` enum value with the specified symbolic representation.
     *
     * @param symbol The symbolic representation of the task status.
     */
    TaskStatus(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbolic representation of the task status.
     *
     * @return A string representation of the task status.
     */
    @Override
    public String toString() {
        return symbol;
    }
}
