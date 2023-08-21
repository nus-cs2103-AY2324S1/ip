public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo initializeFromInput(String input) {
        String taskName = input.split("todo")[1].strip();
        return new Todo(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}