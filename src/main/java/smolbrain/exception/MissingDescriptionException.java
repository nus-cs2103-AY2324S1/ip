package smolbrain.exception;

/**
 * Handles when task description is missing.
 */
public class MissingDescriptionException extends Exception{

    String task_type;

    /**
     * Creates the exception.
     *
     * @param task_type Type of task involved.
     */
    public MissingDescriptionException(String task_type) {
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
        return "The description of a " + task_type + " cannot be empty.";
    }

}
