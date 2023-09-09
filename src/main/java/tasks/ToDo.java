package tasks;

/**
 * The `ToDo` class represents a basic to-do task without a specific deadline or event time.
 * It extends the `Task` class and inherits its methods.
 */
public class ToDo extends Task {

    /**
     * Constructs a `ToDo` object with the specified description.
     *
     * @param text The description of the to-do task.
     */
    public ToDo(String text) {
        super(text);
    }

    /**
     * Converts the `ToDo` object to a string in a save file format.
     *
     * @return A string representation of the `ToDo` object in save file format.
     */
    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }

    /**
     * Returns a string representation of the `ToDo` object for displaying to the user.
     *
     * @return A string representation of the `ToDo` object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

