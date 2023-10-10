package duke;

/**
 * The ToDo class represents a task.
 * It extends the Task class and provides methods to mark and unmark the task as done.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDos instance with the specified task description.
     *
     * @param task The description of the task.
     */
    public ToDo(String task) {
        super(task);
    }

    @Override
    public void mark() {
        super.mark();
    }
    @Override
    public void unMark() {
        super.unMark();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
