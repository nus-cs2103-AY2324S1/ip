package commands;

public class InvalidCommandException extends Exception{
    public InvalidCommandException() {
        super("Invalid command is provided!");
    }
}
