public class DeadlineException extends DukeException{

    public DeadlineException(String message) {
        super(message + "Enter in the form: \"deadline [task_description] /by {d/M/yyyy HH:mm}\"");
    }
}
