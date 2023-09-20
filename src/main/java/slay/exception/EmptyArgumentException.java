package slay.exception;

public class EmptyArgumentException extends Exception {
    public EmptyArgumentException() {
        super("Error: Empty argument found.");
    }
}
