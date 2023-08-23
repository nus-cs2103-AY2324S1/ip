public class Todo extends Task {
    /** Constructor for Todo.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /** toString method for Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
