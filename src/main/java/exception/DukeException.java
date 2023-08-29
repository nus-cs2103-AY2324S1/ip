package exception;

public class DukeException extends Exception{
    public DukeException(String errorMsg, Throwable err) {
        super(errorMsg, err);
    }
}
