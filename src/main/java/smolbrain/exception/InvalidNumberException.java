package smolbrain.exception;

/**
 * Handles an invalid number given by user.
 */
public class InvalidNumberException extends Exception {

    /** Type of task */
    private String taskType;

    /**
     * Creates the exception.
     *
     * @param taskType Type of task involved.
     */
    public InvalidNumberException(String taskType) {
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
        return "Please provide a valid number to " + taskType + ".";
    }

}
