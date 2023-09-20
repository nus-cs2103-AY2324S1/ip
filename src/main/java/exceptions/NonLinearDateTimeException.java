package exceptions;

public class NonLinearDateTimeException extends DukeException {
    String msg;
    public NonLinearDateTimeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "OOPS! " + this.msg;
    }
}
