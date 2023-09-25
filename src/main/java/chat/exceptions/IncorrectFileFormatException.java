package chat.exceptions;

/**
 * @author juzzztinsoong
 */
public class IncorrectFileFormatException extends ChatException {
    public IncorrectFileFormatException() {
        super("File either has a wrong format or is out of date!");
    }
}
