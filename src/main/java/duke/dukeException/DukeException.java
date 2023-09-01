package duke.dukeException;

/**
 * A general exception that catches all the error that duke.Duke might make.
 */
public class DukeException extends Exception {

    /**
     * DukeException constructor.
     * @param errMsg The error message.
     */
    public DukeException(String errMsg)  {
        super(errMsg);
    }



}
