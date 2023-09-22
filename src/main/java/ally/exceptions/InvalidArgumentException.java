package ally.exceptions;

public class InvalidArgumentException extends AllyException {
    public InvalidArgumentException(String message) {
        super("YOO, that's not right, please type 'help' to view the list of commands." + message);
    }
}
