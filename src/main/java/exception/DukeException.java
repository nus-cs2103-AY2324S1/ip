package exception;

/**
 * Custom Exception to handle exception thrown by Duke chatbot.
 * @author syamfarh
 */
public class DukeException extends Exception {
    public DukeException(String errorMsg, Throwable err) {
        super(errorMsg, err);
    }
}
