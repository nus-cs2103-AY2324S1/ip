package exception;

public class InvalidDateTimeException extends DukeException {
    String message;
    public InvalidDateTimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The date/time format of the "
                + this.message + " is incorrect :((("
                + "\n-----------------------------------------------------------------";
    }
}
