public class EventException extends DukeException{

    public EventException(String message) {
        super(message + "Enter in the form: \"event [task_description] /from {d/M/yyyy HH:mm} /by {d/M/yyyy HH:mm}\"");
    }
}
