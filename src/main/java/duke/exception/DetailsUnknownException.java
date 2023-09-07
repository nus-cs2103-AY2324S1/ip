package duke.exception;

/**
 * When the user did not enter the details of the task
 */
public class DetailsUnknownException extends ChattyException {
    /**
     * Returns a message telling the user that the input entered is incomplete
     */
    public DetailsUnknownException() {

        super("The details are missing. Please check your input again");
    }
}
