public class MissingTaskException extends DukeException {
    public MissingTaskException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Task cannot be found :(";
    }
}
