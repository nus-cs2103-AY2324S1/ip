package ren;

public class InvalidRenCommand extends RenException {
    public InvalidRenCommand() {
        super("Please input a valid command!");
    }
}
