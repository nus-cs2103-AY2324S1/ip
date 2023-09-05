package duke.exception;

/**
 * Represents an exception that occurs when the user input for a todo instruction is invalid.
 * This exception is thrown when the todo command is not followed by a task description to be added to the list.
 */
public class ToDoCommandUseException extends Exception{

    /**
     * Constructs a ToDoCommandUseException with a custom error message.
     *
     * @param message The error message indicating the issue with the todo command input.
     */
    public ToDoCommandUseException(String message) {
        super(message + ":" + " Accio error! todo must be followed by a task to be added to the list");
    }
}
