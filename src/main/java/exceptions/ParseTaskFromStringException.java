package exceptions;

public class ParseTaskFromStringException extends DukeException {
    String msg;

    public ParseTaskFromStringException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Saved task data has been corrupted. Cannot parse task from: " + this.msg;
    }
}
