package duke.exception;

public class IncompleteMessageException extends ChattyException {
    public IncompleteMessageException(String errorMessage) {
        super("The command you entered is incomplete. Please give me more information.");
    }
}
