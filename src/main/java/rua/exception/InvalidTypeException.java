package rua.exception;

public class InvalidTypeException extends Exception {
    private final String type;

    public InvalidTypeException(String type) {
        this.type = type;
    }

    public String toString() {
        return (" OOPS!!! I'm sorry, but type " + type + " is not supported :-(\n");
    }
}
