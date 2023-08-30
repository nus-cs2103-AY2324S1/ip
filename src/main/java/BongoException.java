public class BongoException extends Exception {
    public BongoException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public BongoException(String message) {
        super(message);
    }
}
