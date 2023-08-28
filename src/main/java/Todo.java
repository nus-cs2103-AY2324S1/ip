public class Todo extends Task {
    /**
     * Constructor for the Todo class
     *
     * @param task  - the description of the task created
     * @param input - Input that generated the task
     */
    public Todo(String task, String input) {
        super(task, input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
