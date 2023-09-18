package data.exception;

import ui.UiCli;

/**
 * The custom {@link Exception} class for Duke.
 * Used by {@link UiCli} to print error messages.
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
        this.msg = String.format("%s", msg);
    }

    /**
     * The constructor method of the DukeException class.
     * Takes in an array of messages.
     * 
     * @param msg An array of messages containing the 
     *            description of the error.
     */
    public DukeException(String[] msg) {
        StringBuilder temp = new StringBuilder();
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
