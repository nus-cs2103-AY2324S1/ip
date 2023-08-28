/**
 * Exception class that indicates the command is typed wrongly
 */
public class InvalidCommandSyntaxException extends Exception {
    public InvalidCommandSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}
