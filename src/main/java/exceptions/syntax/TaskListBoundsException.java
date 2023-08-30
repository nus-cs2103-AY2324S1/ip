package exceptions.syntax;

/**
 * Thrown when the user tries to perform an operation on an index out of bounds of a TaskList
 */
public class TaskListBoundsException extends ArgErrorException {

    /**
     * Constructor for a TaskListBoundsException
     *
     * @param listSize       the size of the list that was attempted to be accessed
     * @param attemptedIndex the message the user attempted to access
     * @param cause          what caused this, see java.Exceptions for more details,
     *                       typically pass in whatever out-of-bounds exception forced us to throw this
     */
    public TaskListBoundsException(int listSize, int attemptedIndex, IndexOutOfBoundsException cause) {
        super(String.format("User tried to access index %s, but list was of size %s",
                        attemptedIndex, listSize),
                String.format("Your index %s cannot be accepted, only indices 1 - %s are valid!",
                        attemptedIndex + 1, listSize), //user expected 1-indexed
                cause);


    }
}
