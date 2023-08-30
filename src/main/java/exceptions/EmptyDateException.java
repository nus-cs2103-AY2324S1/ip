package exceptions;

public class EmptyDateException extends DukeException {
    String msg;
    public EmptyDateException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The date of a(n) " + this.msg + " cannot be empty.\n" +
                "    ____________________________________________________________";
    }
}
