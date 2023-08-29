package exception;

public class EmptyInputException extends DukeException {
    protected String message;

    public EmptyInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + this.message + " cannot be empty."
                + "\n-----------------------------------------------------------------";
    }
}
