package duke;

// Solution below inspired by https://stackify.com/specify-handle-exceptions-java/
/**
 * Custom exceptions used in the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * An exception class for handling cases where the user attempts to create a todo task with an empty description.
     */
    public static class ToDoException extends DukeException{
        public ToDoException() {
            super("todo cannot be empty");
        }
    }

    /**
     * An exception class for handling cases where the user provides an invalid command.
     */
    public static class NoSuchItemException extends DukeException{
        public NoSuchItemException() {
            super("invalid command");
        }
    }

    /**
     * An exception class for handling cases where the user attempts to create an event task with an empty description.
     */
    public static class EventException extends DukeException{
        public EventException() {
            super("event cannot be empty");
        }
    }

    /**
     * An exception class for handling cases where the user attempts to create a deadline task with an empty description.
     */
    public static class DeadlineException extends DukeException{
        public DeadlineException() {
            super("deadline cannot be empty");
        }
    }

    /**
     * An exception class for handling cases where the user attempts to mark a task without specifying the task.
     */
    public static class MarkException extends DukeException{
        public MarkException() {
            super("Task to mark not specified");
        }
    }

    /**
     * An exception class for handling cases where the user attempts to unmark a task without specifying the task.
     */
    public static class UnmarkException extends DukeException{
        public UnmarkException() {
            super("Task to unmark not specified");
        }
    }

    /**
     * An exception class for handling cases where the user provides a deadline in an incorrect format.
     */
    public static class DeadlineFormatException extends DukeException{
        public DeadlineFormatException() {
            super("Deadline not in the correct format");
        }
    }

    /**
     * An exception class for handling cases where the user provides an event in an incorrect format.
     */
    public static class EventFormatException extends DukeException{
        public EventFormatException() {
            super("Event not in the correct format");
        }
    }
}
