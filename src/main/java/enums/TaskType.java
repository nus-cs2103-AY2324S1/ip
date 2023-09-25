package enums;

/**
 * The `TaskType` enum represents the different types of tasks in the Woof application.
 */
public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String symbol;

    /**
     * Constructs a `TaskType` enum with the given symbol.
     *
     * @param symbol The symbol associated with the task type.
     */
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with the `TaskType`.
     *
     * @return The symbol associated with the `TaskType`.
     */
    public String toSymbol() {
        return this.symbol;
    }
}
