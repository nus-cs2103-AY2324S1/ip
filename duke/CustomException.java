package duke;

public class CustomException extends Exception {

    public CustomException() {
        super("Default custom exception message");
    }

    public CustomException(String message) {
        super(message);
    }
}
