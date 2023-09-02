package duke;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor to initialise a Todo object.
     *
     * @param desc Description of the Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Converts the Todo object to the specific format to be stored in the text file.
     *
     * @return The specific string representation of the Todo object to be stored.
     */
    @Override
    public String convertToSavedString() {
        return String.format("[T] %s//", super.convertToSavedString());
    }

    /**
     * Converts the Todo object to its string representation.
     *
     * @return The string representation of the Todo object.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
