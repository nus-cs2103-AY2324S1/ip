public class DeadlineException extends Exception {
    public DeadlineException(String message) {
        super("DeadlineException: "+ message + "\n");
    }
}