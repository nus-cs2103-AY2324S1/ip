package pau.exception;

/**
 * Exception for an invalid index to get task.
 */
public class NoSuchTaskException extends Exception{
    /**
     * Constructs a NoSuchTaskException with an error message.
     *
     * @param errorMessage Error message that is printed when exception is thrown.
     */
    public NoSuchTaskException(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println("there is no such task!!");
    }
}