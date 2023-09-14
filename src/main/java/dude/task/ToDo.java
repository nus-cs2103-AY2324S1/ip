package dude.task;

/**
 * Represents a todo that extends a task.
 * A <code>ToDo</code> object has a <code>String</code> description.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the specified description.
     *
     * @param description A short description of the event.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String saveTask() {
        String data = "T " + super.saveTask() + "\n";
        return data;
    }

    /**
     * Returns a formatted string representation of the todo.
     *
     * @return A formatted string describing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
