package tasks;
/**
 * Represents a task with no additional attributes.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the compact saved format for this To Do.
     * @return String
     */
    @Override
    public String getSaveFormat() {
        return String.format("T | %c | %s",
                this.getDoneSymbol(),
                this.description);
    }

    /**
     * String representation of this To Do as a task.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[T][%c] %s",
                this.getDoneSymbol(),
                this.description);
    }
}