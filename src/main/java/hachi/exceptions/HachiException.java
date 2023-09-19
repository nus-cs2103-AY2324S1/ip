package hachi.exceptions;

/**
 * Parent exception class to encapsulate all exceptions thrown by Hachi.
 */
public class HachiException extends Exception {
    public HachiException(String errorMsg) {
        super(errorMsg);
    }
}
