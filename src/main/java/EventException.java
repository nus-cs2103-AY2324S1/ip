public class EventException extends DukeException{

    public EventException(String message) {
        super(message + "Please enter in the form: event [task_description] /from [start_date] /by [end_date]");
    }
}
