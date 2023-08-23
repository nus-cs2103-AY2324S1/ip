public class EventException extends Exception {
    public EventException(String message) {
        super("EventException: " + message + "\n");
    }
}