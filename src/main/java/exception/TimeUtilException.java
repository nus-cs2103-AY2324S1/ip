package exception;

public class TimeUtilException extends DukeException {
    public TimeUtilException(String message) {
        super(String.format("%s %s", "[TimeUtil]", message));
    }
}
