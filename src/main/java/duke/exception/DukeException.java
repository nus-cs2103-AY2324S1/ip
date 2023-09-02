package duke.exception;

/** This class handles exceptions from Duke bot.*/
public class DukeException extends Exception {

    /**
     * The constructor for DukeException
     * @param errorMsg The error message for the Duke exception
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}