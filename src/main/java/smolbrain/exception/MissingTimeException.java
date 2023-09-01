package smolbrain.exception;

/**
 * Handles when required time for task is missing.
 */
public class MissingTimeException extends Exception{

    String task_type;
    String time_type;

    /**
     * Creates the exception.
     *
     * @param time_type Type of time that was missing.
     * @param task_type Type of task involved.
     */
    public MissingTimeException(String time_type, String task_type) {
        super();
        this.task_type = task_type;
        this.time_type = time_type;
    }

    /**
     * Returns the string representation of the exception, specified with the time and task type.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "The " + time_type + " time of a " + task_type + " cannot be empty.";
    }

}