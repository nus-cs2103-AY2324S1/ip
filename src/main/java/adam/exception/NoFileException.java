package adam.exception;

/**
 * This exception is thrown when user tries access a file that doesn't exist.
 */
public class NoFileException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! This file doesn't exist";
    }
}
