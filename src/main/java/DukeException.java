// Solution below inspired by https://stackify.com/specify-handle-exceptions-java/
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static class ToDoException extends DukeException{
        public ToDoException() {
            super("todo cannot be empty");
        }
    }

    public static class NoSuchItemException extends DukeException{
        public NoSuchItemException() {
            super("invalid command");
        }
    }
    public static class EventException extends DukeException{
        public EventException() {
            super("event cannot be empty");
        }
    }

    public static class DeadlineException extends DukeException{
        public DeadlineException() {
            super("deadline cannot be empty");
        }
    }

    public static class MarkException extends DukeException{
        public MarkException() {
            super("Task to mark not specified");
        }
    }

    public static class UnmarkException extends DukeException{
        public UnmarkException() {
            super("Task to unmark not specified");
        }
    }

    public static class DeadlineFormatException extends DukeException{
        public DeadlineFormatException() {
            super("Deadline not in the correct format");
        }
    }

    public static class EventFormatException extends DukeException{
        public EventFormatException() {
            super("Event not in the correct format");
        }
    }
}
