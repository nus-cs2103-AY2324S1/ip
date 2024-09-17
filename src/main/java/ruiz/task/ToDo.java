package ruiz.task;

/**
 * Represents a task that just has a description.
 */
public class ToDo extends Task {
    protected String location;

    /**
     * A constructor for the public ToDo class.
     *
     * @param description contains the description of the ToDo
     */
    public ToDo(String description, String location) {
        super(description, location);
        this.location = location;
    }

    @Override
    public String formatSaveTaskString() {
        return "T" + super.formatSaveTaskString() + " | " + this.location;
    }

    /**
     * This method converts the value of an Event into a String type.
     *
     * @return the String representation of the ToDo with its type and completion status.
     */
    @Override
    public String toString() {
        return "[T]"
                + super.toString()
                + " at: "
                + this.location;
    }
}
