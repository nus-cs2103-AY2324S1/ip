package slay.exception;

/**
 * Signals that the command is expecting an argument but receives empty argument.
 */
public class EmptyArgumentException extends Exception {
    public EmptyArgumentException() {
        super("Error: Empty argument found.");
    }
}
