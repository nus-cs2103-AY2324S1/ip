package types;

/**
 * Represents the subset of Tasks that only have a description.
 */
public class Todo extends types.Task {
    /**
     * Initialises an instance of a Todo.
     *
     * @param description description of the Task to be completed
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString() method to return a customised String for each Todo.
     *
     * @return String with "T" type
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
