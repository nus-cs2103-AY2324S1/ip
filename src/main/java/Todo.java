public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * returns string representation of todo task.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
