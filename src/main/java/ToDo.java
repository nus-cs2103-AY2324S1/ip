public class ToDo extends Task {
    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Prints out the description of the task and its status.
     * @return A string that shows the task's description and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}