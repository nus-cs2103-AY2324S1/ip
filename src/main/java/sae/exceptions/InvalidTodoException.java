package sae.exceptions;

/**
 * Custom exception class representing an error when the description of a todo task is empty.
 */
public class InvalidTodoException extends SaeException {

    /**
     * Returns a string representation of the exception message.
     *
     * @return A message indicating that the description of a todo cannot be empty.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The todo command should be followed by a task description";
    }
}
