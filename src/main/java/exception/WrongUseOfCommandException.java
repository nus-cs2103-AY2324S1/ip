package exception;

public class WrongUseOfCommandException extends DukeException {
    public WrongUseOfCommandException() {
        super("No extra text after this command bro..");
    }
}
