package chatty.exception;

/**
 * When user only enter the keyword without the task description
 */
public class IncompleteMessageException extends ChattyException {
    public IncompleteMessageException(String errorMessage) {
        super("The command you entered is incomplete. Please give me more information.");
    }
}
