package ren;

public class InvalidRenCommandException extends RenException {
    public InvalidRenCommandException() {
        super("Please input a valid command!");
    }
}
