package duke.userio;

public class InvalidUserInputException extends Exception {
    public InvalidUserInputException() {
        super("Invalid user input is detected");
    }
}
