package minion.data.task;

/**
 * Represents the symbols associated with a particular type of task.
 */
public enum TaskSymbol {
    TODO('T'), DEADLINE('D'), EVENT('E');

    private final char symbol;

    private TaskSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
