package exception;

public class InvalidFormatException extends DukeException {
    protected String message;

    protected String subMessage;

    public InvalidFormatException(String message, String subMessage) {
        this.message = message;
        this.subMessage = subMessage;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The format of the " + this.message + " is incorrect :(((\n"
                + "Please add a \"" + this.subMessage + "\" keyword in"
                + "\n-----------------------------------------------------------------";
    }
}
