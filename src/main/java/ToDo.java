/**
 * Represent a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Creates a todo task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
