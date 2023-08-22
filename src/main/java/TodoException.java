public class TodoException extends DukeException{

    public TodoException(String message) {
        super(message + "Please enter in the form: todo [task_description]");
    }
}
