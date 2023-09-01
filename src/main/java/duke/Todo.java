package duke;

/**
 * A Todo is a type of task.
 */
public class Todo extends Task {

    /**
     * Constructor method for Event.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String printDesc() {
        return "[T]" + super.printDesc();
    }
    @Override
    public String getDescription() {
        return "T~" + super.getDescription();
    }
}
