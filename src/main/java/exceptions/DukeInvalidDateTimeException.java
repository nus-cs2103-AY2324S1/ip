package exceptions;

public class DukeInvalidDateTimeException extends Exception{
    public DukeInvalidDateTimeException(String errorString) {
        super(errorString);
    }
}
