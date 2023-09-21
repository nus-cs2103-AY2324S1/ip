package duke.exception;

/** An exception thrown when the ranking of task cannot be done. */
public class InvalidRankingException extends DukeException{
    /**
     * Constructs an InvalidRankingException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidRankingException(String s) {
        super(s);
    }
}
