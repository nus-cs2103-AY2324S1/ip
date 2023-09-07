package duke.exceptions;

public class InvalidFormatException extends DukeException {
    private String incorrectInput;
    private String incorrectReason;
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
