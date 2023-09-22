package duke.tasks;

/**
 * A Task to be done.
 */
public class ToDo extends Task {

    /**
     * The Constructor for a ToDo Task.
     *
     * @param description The description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the Formatted String to be saved into Storage.
     *
     * @return Formatted String representation of the ToDo task.
     */
    public String exportData() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Returns the String representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
