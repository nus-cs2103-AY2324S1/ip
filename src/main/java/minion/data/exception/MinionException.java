package minion.data.exception;

/**
 * A general exception for the Minion chatbot. It is a superclass of IllegalValueException and ParserException.
 */
public class MinionException extends Exception {
    /**
     * Constructs a MinionException.
     * @param s error message.
     */
    public MinionException(String s) {
        super(s);
    }
}
