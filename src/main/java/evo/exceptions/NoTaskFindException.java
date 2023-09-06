package evo.exceptions;

/**
 * This exception is thrown to indicate that no tasks in the tasksList.
 * It typically occurs when performing a find operation, and there are no tasks in the tasksList.
 * This exception allows for handling situations where no tasks in the tasksList during a find operation.
 *
 * @author NgChunMan
 */
public class NoTaskFindException extends Exception {

    /**
     * Constructs a new NoTaskFindException with the specified error message.
     *
     * @param errorMessage A string that describes the specific error or reason for the exception.
     */
    public NoTaskFindException(String errorMessage) {
        super(errorMessage);
    }
}
