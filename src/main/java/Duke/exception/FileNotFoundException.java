package Duke.exception;

import Duke.message.ErrorMessage;
public class FileNotFoundException extends DukeException {
    public FileNotFoundException(String name) {
        super(name);
    }
    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage("There is no file at " + content);
    }
}
