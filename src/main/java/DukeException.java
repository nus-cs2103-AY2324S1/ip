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
}
