package smolbrain.exception;

/**
 * Handles an invalid number given by user.
 */
public class InvalidNumberException extends Exception{

    String task_type;

    /**
     * Creates the exception.
     *
     * @param task_type Type of task involved.
     */
    public InvalidNumberException(String task_type) {
        super();
        this.task_type = task_type;
    }

    /**
     * Returns the string representation of the exception, specified with the task type.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Please provide a valid number to " + task_type + ".";
    }

}