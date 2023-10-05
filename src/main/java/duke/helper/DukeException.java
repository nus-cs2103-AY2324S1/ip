package duke.helper;

/**
 * Duke Exception class
 */
public class DukeException extends Exception {

    /**
    * Constucts the DukeException class.
    *
    * @param message the message of the exception
    */
    public DukeException(String message) {
        super(message);
    }

    /**
    * returns the detail message of this throwable
    *
    * @return the message of the exception
    */
    @Override
    public String getMessage() {
        return ":( Oh no! " + super.getMessage();
    }

    /**
    * returns the detail message of this throwable
    *
    * @return the message of the exception
    */
    @Override
    public String toString() {
        return ":( Oh no! " + super.getMessage();
    }
}
