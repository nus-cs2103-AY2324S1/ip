package duke;

public class GmanException extends Exception {
    public GmanException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
