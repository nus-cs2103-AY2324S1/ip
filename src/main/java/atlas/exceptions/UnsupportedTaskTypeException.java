package atlas.exceptions;

/**
 * Exception class for unsupported task types
 */
public class UnsupportedTaskTypeException extends RuntimeException {
    protected final String taskType;

    /**
     * Constructs an UnsupportedTaskType exception
     * @param taskType Name of illegal task type
     */
    public UnsupportedTaskTypeException(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
