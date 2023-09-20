package ekud.tasks;

/**
 * Represents symbols for identifying different task types.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");
    private String symbol;
    private TaskType(String symbol) {
        this.symbol = symbol;
    }
    public static TaskType getTaskType(String inputSymbol) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.symbol.equals(inputSymbol)) {
                return taskType;
            }
        }
        return null;
    }
}
