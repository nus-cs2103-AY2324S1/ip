package duke.exception;

public class InvalidTaskNumberException extends ChattyException {

    public InvalidTaskNumberException() {
        super("The task number you have entered is invalid, please double check.");
    }
}
