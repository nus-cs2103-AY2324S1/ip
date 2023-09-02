package duke.task;

/**
 * Represents a DeadlinesTask.
 */
public class DeadlinesTask extends Task {
    private final String type = "D";
    private final String deadline;

    /**
     * Constructor for DeadlinesTask.
     * @param name Name of DeadlinesTask.
     * @param deadline Deadline of DeadlinesTask.
     */
    public DeadlinesTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of DeadlinesTask.
     * @return String representation of DeadlinesTask.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", type, super.toString(), this.deadline);
    }

    /**
     * Returns the string representation of DeadlinesTask to store.
     * @return String representation of DeadlinesTask to store.
     */
    @Override
    public String toStringStore() {
        String mark = this.isDone ? "1" : "0";
        return String.format("%s,%s,%s,%s", type, mark, this.name, this.deadline);
    }
}
