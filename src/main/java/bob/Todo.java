package bob;

/**
 * Represents a todo task that contains a description
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class
     *
     * @param description the name/description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo, including its type of task, completion status,
     * and description
     *
     * @return the string representation of the todo
     */

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
