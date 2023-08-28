package duke;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Invalid command given.");
    }

}
