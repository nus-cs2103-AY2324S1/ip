public class ToDoCommandUseException extends Exception{
    public ToDoCommandUseException(String message) {
        super(message + ":" + " todo must be followed by a task to be added to the list");
    }
}
