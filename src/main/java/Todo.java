public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo initializeFromInput(String input) throws EmptyDescriptionException {
        try {
            String taskName = input.split("todo")[1].strip();
            return new Todo(taskName);
        } catch (Exception e) {
            throw new EmptyDescriptionException("todo", "todo borrow book");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}