package bruno.exceptions;

/**
 * The BrunoIncorrectFormatException handles the case where the data in the file to be read is not in
 * proper format.
 */
public class BrunoIncorrectFormatException extends BrunoException {

    public BrunoIncorrectFormatException() {
        super("File is not in correct format.");
    }
}
