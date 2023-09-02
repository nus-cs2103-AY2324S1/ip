package duke.dukeexceptions;

public class InvalidCommandException extends Exception {
    private final String corruptInput;
    public InvalidCommandException(String corruptInput) {
        super("Command is of invalid format");
        this.corruptInput = corruptInput;
    }

    public String getCorruptInput() {
        return this.corruptInput;
    }
}
