package yong.exception;

/**
 * Class to handle exceptions due to incorrect user input.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
