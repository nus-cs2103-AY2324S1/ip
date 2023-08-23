public class TodoException extends Exception {
    public TodoException(String message) {
        super("TodoException: "+ message + "\n");
    }
}