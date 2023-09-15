package evo.exceptions;

/**
 * This exception is thrown to indicate that there is no keyword specified for finding a task within a task list.
 * It typically occurs when attempting to perform a find operation without providing a keyword.
 * This exception allows for handling situations where a keyword to find a specific task is missing.
 *
 * @author NgChunMan
 */
public class MissingTaskToFindException extends Exception {

    /**
     * Constructs a new MissingTaskToFindException with the specified error message.
     *
     * @param errorMessage A string that describes the specific error or reason for the exception.
     */
    public MissingTaskToFindException(String errorMessage) {
        super(errorMessage);
    }
}
