package smolbrain.exception;

/**
 * Handles when required time for task is missing.
 */
public class MissingTimeException extends Exception {

    private String taskType;
    private String timeType;

    /**
     * Creates the exception.
     *
     * @param taskType Type of time that was missing.
     * @param timeType Type of task involved.
     */
    public MissingTimeException(String taskType, String timeType) {
        super();
        this.taskType = taskType;
        this.timeType = timeType;
    }

    /**
     * Returns the string representation of the exception, specified with the time and task type.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "The " + timeType + " time of a " + taskType + " cannot be empty.";
    }

}
