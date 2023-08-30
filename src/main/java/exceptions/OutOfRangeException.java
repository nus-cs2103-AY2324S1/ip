package exceptions;

public class OutOfRangeException extends DukeException {
    String msg;

    public OutOfRangeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! " + this.msg + " number out of range.\n" +
                "    ____________________________________________________________";
    }
}
