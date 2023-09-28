package potato.task;

import potato.PotatoException;

/**
 * The Todo class represents a task without a specific deadline or event time.
 * It extends the Task class and adds functionality for todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the provided description, completion status, and priority.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     * @param priority    The priority of the todo task.
     */
    public Todo(String description, boolean isDone, String priority) {
        super(description, isDone, priority);
    }

    /**
     * Parses a user input string to create a Todo object.
     *
     * @param input The user input string representing the todo task.
     * @param mark  The marker indicating the completion status (e.g., "1" for completed, "0" for incomplete).
     * @return A Todo object created from the input.
     * @throws PotatoException If the input is empty or invalid.
     */
    public static Todo parseTodo(String input, String mark) {

        if (input.length() < 1) {
            throw new PotatoException("Bruh you wanna do air or something?\n");
        } else {
            assert input.length() > 1 : "input length should be > 1";
            return new Todo(input, (mark.equals("1")), "NIL");
        }
    }

    /**
     * Parses a saved representation of a Todo object.
     *
     * @param input The string array containing the saved representation of the todo task.
     * @return A Todo object created from the saved data.
     */
    public static Todo parseSavedTodo(String[] input) {
        return new Todo(input[2], input[1].equals("1"), input[3]);
    }

    /**
     * Converts the Todo object to a string representation suitable for saving.
     *
     * @return A string representation of the Todo object for saving.
     */
    @Override
    public String toSave() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + priority.toUpperCase();
    }

    /**
     * Converts the Todo object to a string for displaying to the user.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
