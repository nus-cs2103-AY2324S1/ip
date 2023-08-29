package Exception;

public class MissingTaskException extends DukeException {
    public MissingTaskException(String command) {
        super(command + " needs a task after it...");
    }
}