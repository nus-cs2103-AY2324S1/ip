package duke.task;

import duke.exception.EmptyDescriptionException;

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

    public static Todo initializeFromStorage(String input) {
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
