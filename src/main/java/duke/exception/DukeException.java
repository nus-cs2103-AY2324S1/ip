package duke.exception;

/** This class handles exceptions from duke.main.Duke.*/
public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}