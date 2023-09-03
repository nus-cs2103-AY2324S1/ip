package kiera.exception;

/**
 * Exception class to catch runtime exceptions.
 */
public class KieraException extends RuntimeException {
    public KieraException(String e) {
        super(e);
    }
    public String toString() {
        return this.getMessage();
    }
}
