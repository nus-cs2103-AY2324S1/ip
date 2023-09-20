package duke;

/**
 * This class encapsulates a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToSaveFormat() {
        return "T | " + super.convertToSaveFormat();
    }
}
