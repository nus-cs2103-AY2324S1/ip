package duke.dukeexceptions;

public class InvalidCommandException extends Exception {
    private final String message;

    public InvalidCommandException() {
        super();
        this.message = "";
    }
    public InvalidCommandException(String message) {
        super();
        this.message = message;
    }

    public String getBotMessage() {
        return this.message;
    }
}
