package jarvis.tasks;

/**
 * TaskType Enumeration Class.
 *
 * @author Shishir
 */
public enum TaskType {
    EVENT("event"),
    TODO("todo"),
    DEADLINE("deadline");
    private final String taskType;
    TaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns string representation of task type.
     * @return String representation of task type.
     */
    @Override
    public String toString() {
        return this.taskType;
    }
}
