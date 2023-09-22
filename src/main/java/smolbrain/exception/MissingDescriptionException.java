package smolbrain.exception;

/**
 * Handles when task description is missing.
 */
public class MissingDescriptionException extends Exception {

    /** Type of task */
    private String taskType;

    /**
     * Creates the exception.
     *
     * @param taskType Type of task involved.
     */
    public MissingDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }

    /**
     * Returns the string representation of the exception, specified with the task type.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "The description of a " + taskType + " cannot be empty.";
    }

}
