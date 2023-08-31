package adam.exception;

/**
 * This Exception is a subclass of a RunTimeException and is used when an unidentified user input was entered.
 */
public class AdamException extends RuntimeException{
    /**
     * Returns an error String message.
     *
     * @return String message.
     */
    public String getInfo() {
        return "OOPS!!! I don't know what this means";
    }
}
