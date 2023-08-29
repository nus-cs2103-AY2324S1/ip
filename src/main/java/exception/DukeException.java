package exception;

public class DukeException extends Exception {


    public DukeException() {
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! Something went wrong D:";
    }
}
