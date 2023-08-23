/**
 * The DukeException class contains the error
 * handling for Duke.
 *
 * @author: Shishir
 **/
public class DukeException extends Exception {
    /** The error message. **/
    private String message;

    /** The constructor.
     * @param message The description of the error message.
     **/
    DukeException(String message) {
        super(message);
        this.message = message;
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return this.message;
    }

}
