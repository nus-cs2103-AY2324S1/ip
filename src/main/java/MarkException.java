public class MarkException extends DukeException{

    public MarkException(String message) {
        super(message + "Please enter in the form: mark/unmark [task_number]");
    }
}
