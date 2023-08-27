package duke;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A Checked exception that represents expected errors from the Duke CLI application </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class DukeException extends Exception {
    /**
     * A constructor that constructs the Duke Exception
     * @param errMsg The error message that is supposed to be printed into the command line
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
