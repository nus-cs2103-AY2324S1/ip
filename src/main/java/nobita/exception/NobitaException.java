package nobita.exception;

/**
 *  Class that encapsulates NobitaException.
 *  Throws an exception when error occurs in Nobita.
 *
 *  @author Zheng Chenglong
 */
public class NobitaException extends Exception {

    /**
     * Constructs NobitaException using an error message.
     *
     * @param errorMessage The error message to be printed with the exception.
     */
    public NobitaException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns the String representation of NobitaException.
     *
     * @return String representation of NobitaException.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.toString();
    }
}
