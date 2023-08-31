package duke.data.task;
public class Todo  extends Task {
    /**
     * Constructor to initialize Todo.
     *
     * @param description Description of the Todo task.
     */
    public Todo (String description) {
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
