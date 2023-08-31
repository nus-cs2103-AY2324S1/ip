package exception;

/**
 * Abstract class representing a custom exception for the Duke application.
 * Specific types of exceptions should extend this class.
 */
public abstract class DukeException extends Exception{
    /**
     * Default constructor for exception.DukeException.
     */
    public DukeException(){
        super();
    }

    /**
     * Returns the string representation of the exception.
     * It calls the toString() method from the superclass.
     *
     * @return A string representing the exception.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
