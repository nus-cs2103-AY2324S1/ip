package duke.task;

/**
 * Represents a todo task, containing a description.
 */
public class Todo extends Task {

    /**
     * Returns a new Todo task containing the message.
     *
     * @param message The description for the to-do.
     */
    public Todo(String message) {
        super(message);
    }

    /**
     * Returns a String containing information within the Todo task, formatted to be saved.
     *
     * @return The to-do task, formatted as a String to be saved in the save file.
     */
    public String toSaveFormatString() {
        return "T | " + this.getStatusNumber() + " | " + this.message;
    }

    /**
     * Returns a String representation of the Todo task, formatted for output in the application.
     *
     * @return The to-do task, formatted as a String for output in the application.
     */
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.message;
    }
}
