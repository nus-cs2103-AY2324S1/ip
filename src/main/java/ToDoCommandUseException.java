public class ToDoCommandUseException extends Exception{
    public ToDoCommandUseException(String message) {
        super(message + ":" + " Accio error!todo must be followed by a task to be added to the list");
    }
}
