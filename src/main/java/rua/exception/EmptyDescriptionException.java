package rua.exception;

public class EmptyDescriptionException extends Exception {
    private final String type;

    public EmptyDescriptionException(String type) {
        super();
        this.type = type;
    }

    /**
     * Returns the error message of this exception.
     *
     * @return The error message of this exception.
     */
    public String toString(){
        return (" OOPS!!! The description of a " + type + " cannot be empty.\n");
    }
}
