package ren;

public class InsufficientArgumentsException extends RenException {
    public InsufficientArgumentsException() {
        super("Insufficient command line arguments!");
    }
}
