package duke.exceptions;

/**
 * Encapsulates an error regarding the user's input message.
 */
public class InvalidFormatException extends DukeException {
    private String incorrectInput;
    private String incorrectReason;

    /**
     * Constructor for an invalid format exception.
     *
     * @param incorrectReason The reason why this input is invalid
     * @param incorrectInput  The input that is invalid
     */
    public InvalidFormatException(String incorrectReason, String incorrectInput) {
        super("Error! Your input string (below) was incorrectly formatted!");
        this.incorrectInput = incorrectInput;
        this.incorrectReason = incorrectReason;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + incorrectReason + "\n  " + this.incorrectInput;
    }
}
