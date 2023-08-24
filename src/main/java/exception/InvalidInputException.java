package exception;

public class InvalidInputException extends ChattyException {
    public InvalidInputException() {
        super("Invalid input, please check that you have entered the details correctly");
    }
}
