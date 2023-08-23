public class EventException extends DukeException {
    public EventException() {
        super("The format for adding an event is incorrect. Please use: 'event [description] /from [start] /to [end]'");
    }
}
