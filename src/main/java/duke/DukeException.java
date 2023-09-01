package duke;
public class DukeException extends Exception{

    /**
     * An exception.
     * @param errorMessage The message to be shown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
