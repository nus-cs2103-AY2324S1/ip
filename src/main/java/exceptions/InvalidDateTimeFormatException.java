package exceptions;

import duke.Duke;

public class InvalidDateTimeFormatException extends DukeException {
    String msg;
    public InvalidDateTimeFormatException (String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "Invalid input format! " + this.msg;
    }
}
