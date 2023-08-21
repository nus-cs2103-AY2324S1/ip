public class Todo extends Task {

    /**
     * Constructs a new Todo task.
     *
     * @param description The description of the event.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}