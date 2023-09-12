package chatty.exception;

/**
 * if user entered a task number that does not exist
 */
public class InvalidTaskNumberException extends ChattyException {

    public InvalidTaskNumberException() {
        super("The task number you have entered is invalid, please double check.");
    }
}
