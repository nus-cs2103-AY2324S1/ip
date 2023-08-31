package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of Todo
     *
     * @return A string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
