package nobita.exception;

public class NobitaException extends Exception {
    public NobitaException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.toString();
    }
}
