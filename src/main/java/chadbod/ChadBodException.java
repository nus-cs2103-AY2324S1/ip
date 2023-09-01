package chadbod;

public class ChadBodException extends Exception {
    public ChadBodException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
