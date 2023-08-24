public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of the task.
     * @return Desired representation of the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
