package ruiz.task;

/**
 * Represents a task that just has a description.
 */
public class ToDo extends Task {
    /**
     * A constructor for the public ToDo class.
     *
     * @param description contains the description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String formatSaveTaskString() {
        return "T" + super.formatSaveTaskString();
    }

    /**
     * This method converts the value of an Event into a String type.
     *
     * @return the String representation of the ToDo with its type and completion status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
