package data.exception;

/**
 * The custom {@link Exception} class for Duke.
 */
public class DukeException extends Exception {
    private final String msg;

    /**
     * The constructor method of the DukeException class.
     * Takes in a single line message.
     *
     * @param msg A string containing the
     *            description of the error.
     */
    public DukeException(String msg) {
        this.msg = String.format("Oh no! An error :(\n%s", msg);
    }

    /**
     * The constructor method of the DukeException class.
     * Takes in an array of messages.
     *
     * @param msg An array of messages containing the
     *            description of the error.
     */
    public DukeException(String[] msg) {
        StringBuilder temp = new StringBuilder().append("Oh no! An error :(\n");
        for (String stub : msg) {
            temp.append(String.format("%s\n", stub));
        }
        this.msg = temp.toString().trim().strip();
    }

    @Override
    public String toString() {
        return msg;
    }
}
