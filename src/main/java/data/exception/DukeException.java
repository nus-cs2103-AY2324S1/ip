package data.exception;

/**
 * The custom {@link Exception} class for Duke.
 * Used by {@link ui.Ui} to print error messages.
 */
public class DukeException extends Exception {
    private String msg;

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
        String temp = "";
        for (String stub : msg) {
            temp += String.format("    %s\n", stub);
        }
        this.msg = temp.trim().strip();
    }

    @Override
    public String toString() {
        return msg;
    }
}