package duke.tasks;

/**
 * Enumerates the different types of tasks available in the Duke application.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String taskType;

    /**
     * Constructs a TaskType enumeration.
     *
     * @param taskType The type of the task (e.g., "T" for TO-DO).
     */
    TaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return A string representing the type.
     */
    public String getTaskType() {
        return taskType;
    }
}
