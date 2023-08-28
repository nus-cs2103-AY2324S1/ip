package bruno.exceptions;

public class BrunoIncorrectFormatException extends BrunoException {
    public BrunoIncorrectFormatException() {
        super("File is not in correct format.");
    }
}
