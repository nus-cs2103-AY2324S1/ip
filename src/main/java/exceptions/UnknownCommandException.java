package exceptions;

public class UnknownCommandException extends DukeException {
    String message;

    public UnknownCommandException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString() + this.message;
    }
}
