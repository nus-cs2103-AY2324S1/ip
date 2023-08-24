public class Todo extends Task {

    /**
     * Constructor for todo.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString() method in Task.
     * @return the string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
