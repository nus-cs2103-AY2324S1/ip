package pau.exception;

/**
 * Exception for an invalid description for tasks
 */
public class NoDescException extends Exception {
    /**
     * Constructs a NoDescException with an error message.
     *
     * @param errorMessage Error message that is printed when exception is thrown.
     */
    public NoDescException(String errorMessage) {
        System.out.println("oi write something please");
        System.out.println(errorMessage);
    }
}
