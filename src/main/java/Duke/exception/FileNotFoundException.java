package Duke.exception;

import Duke.message.ErrorMessage;
/**
 * An exception that is thrown when the file cannot be found.
 */
public class FileNotFoundException extends DukeException {
    public FileNotFoundException(String name) {
        super(name);
    }
    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage("There is no file at " + content);
    }
}
