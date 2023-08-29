package exception;

public class EmptyDateTimeException extends DukeException {
    protected String message;

    public EmptyDateTimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The date/time of the " + this.message + " cannot be empty."
                + "\n-----------------------------------------------------------------";
    }
}
