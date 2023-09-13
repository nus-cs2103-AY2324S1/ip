package Tasks;

/**
 * ToDo is a Task in its simplest form - either done or not done.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo instance.
     *
     * @param name The name of the task.
     * @param isDone The completion status of the task.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * Returns the storage string representation format for a ToDo.
     */
    public String toString(boolean isWritten) {
        String completionStr = super.isDone() ? "1" : "0";
        assert completionStr.equals("1") == super.isDone();
        return "T" + " | " + completionStr + " | " + super.getName();
    }
}
