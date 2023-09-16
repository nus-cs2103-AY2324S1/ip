package minion.data.exception;

/**
 * Illegal value exception for invalid arguments.
 */
public class IllegalValueException extends MinionException {
    /**
     * Constructs an IllegalValueException.
     * @param s error message.
     */
    public IllegalValueException(String s) {
        super(s);
    }
}
