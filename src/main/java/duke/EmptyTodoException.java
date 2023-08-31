package duke;

public class EmptyTodoException extends Exception {
    /**
     * Gets the String representation of the Exception.
     *
     * @return String representation of the Exception.
     */
    public String toString() {
        return "Description of Todo cannot be empty.";
    }
}
