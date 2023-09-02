package tasks;

public class Todo extends Task {

    /*
     * Constructor for the Todo object.
     *
     * @params description The description of Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /*
     * Returns the String representation of Todo.
     *
     * @return The String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /*
     * Returns the String representation of Todo.
     *
     * @return The String representation.
     */
    @Override
    public String toStringWithDateTime() {
        return this.toString();
    }
}
