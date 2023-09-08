package duke.exceptions;

/**
 * The Duke exception.
 */
public class DukeException extends Exception{
    /**
     * The description of the error.
     */
    private String msg;

    /**
     * Instantiates a new Duke exception.
     *
     * @param msg the description
     */
    public DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
