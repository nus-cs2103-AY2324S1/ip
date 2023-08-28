package Ren;

public class InsufficientArguments extends RenException {
    public InsufficientArguments() {
        super("Insufficient command line arguments!");
    }
}
