package duke.tasks;

public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "|T| " + super.toString();
    }
}