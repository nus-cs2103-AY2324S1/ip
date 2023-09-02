package duke.task;

/**
 * Represents a TodoTask.
 */
public class TodoTask extends Task {
    private final String type = "T";

    /**
     * Constructor for TodoTask.
     * @param name Name of TodoTask.
     */
    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of TodoTask.
     * @return String representation of TodoTask.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", type, super.toString());
    }

    /**
     * Returns the string representation of TodoTask.
     * @return String representation of TodoTask.
     */
    @Override
    public String toStringStore() {
        String mark = this.isDone ? "1" : "0";
        return String.format("%s,%s,%s", type, mark, this.name);
    }
}
