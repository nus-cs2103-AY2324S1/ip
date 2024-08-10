package duke.data.task;

/**
 * The Todo class is a child class of Task that represents tasks
 * with a description but without a specific deadline or start/end time.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
