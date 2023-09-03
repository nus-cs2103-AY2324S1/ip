public class BareumException extends Exception {
    BareumException(String message) {
        super("Bareum: " + message);
    }
}
