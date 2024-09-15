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
    /**
     * Gets the full description of the todo task.
     * The message here is used to be printed for the user.
     * @return Message for the description of the todo task.
     */
    @Override
    public String printDesc() {
        return "[T]" + super.printDesc();
    }
    /**
     * Gets the full description of the todo task.
     * The message here is used to be stored in the duke.txt.
     * @return Message for the description of the todo task.
     */
    @Override
    public String getDescription() {
        return "T~" + super.getDescription();
    }
}
