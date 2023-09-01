package haste.exceptions;

public class InvalidCommand extends Exception {
    public InvalidCommand(String cmd) {
        super(cmd + " is an invalid command!");
    }
}
