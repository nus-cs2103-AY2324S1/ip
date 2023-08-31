public class EventException extends DukeException {
    public EventException() {
        super("The format for adding an event is incorrect. Please use: 'event [description] /from [yyyy-MM-dd] /to [yyyy-MM-dd]'");
    }

    public EventException(String message) {
        super("The format for adding an event is incorrect. Please use: 'event [description] /from [yyyy-MM-dd] /to [yyyy-MM-dd]'"
                + "\n" + message);
    }
}
