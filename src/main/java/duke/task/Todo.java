package duke.task;

import duke.exception.EmptyDescriptionException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialize a new Todo from user input.
     *
     * @param input User input.
     * @return A new Todo object.
     * @throws EmptyDescriptionException If todo description is empty.
     */
    public static Todo initializeFromInput(String input) throws EmptyDescriptionException {
        try {
            String taskName = input.split("todo")[1].strip();
            return new Todo(taskName);
        } catch (Exception e) {
            throw new EmptyDescriptionException("todo", "todo borrow book");
        }
    }


    /**
     * Initialize a new Todo from file storage.
     *
     * @param input Line from file storage.
     * @return A new Todo object.
     */
    public static Todo initializeFromStorage(String input) {
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
