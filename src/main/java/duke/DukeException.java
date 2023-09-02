package duke;

/**
 * A class which represents all the exceptions generated in this bot.
 *
 */
public class DukeException extends Exception {

    /**
     * A constructor which initialises the message of this exception.
     *
     * @param errorMessage The message to initialise with.
     */
    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
