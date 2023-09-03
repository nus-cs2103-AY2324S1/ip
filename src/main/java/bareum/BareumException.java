package bareum;

public class BareumException extends Exception {
    public BareumException(String message) {
        super("bareum.Bareum: " + message);
    }
}
