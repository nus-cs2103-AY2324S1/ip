package exceptions;

public class EmptyTaskException extends DukeException {
    private String taskName;
    public EmptyTaskException(String taskName) {
        super("empty task");
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return super.toString() + " The description of a " + this.taskName + " cannot be empty.";
    }
}
