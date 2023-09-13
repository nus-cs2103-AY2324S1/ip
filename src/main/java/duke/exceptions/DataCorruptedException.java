package duke.exceptions;

/**
 * Exception is thrown when the data file contains text not of the Task format.
 */
public class DataCorruptedException extends DukeException {
    public DataCorruptedException() {
        super("(・´з`・) Uh oh... data is corrupted. Please delete the file and retry.");
    }
}
