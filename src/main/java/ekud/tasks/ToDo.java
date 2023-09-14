package ekud.tasks;
/**
 * Represents a task with no additional attributes.
 */
public class ToDo extends Task {
    public ToDo(String description, Priority priority) {
        super(description, priority);
    }

    /**
     * Returns the compact saved format for this To Do.
     * @return String
     */
    @Override
    public String getSaveFormat() {
        return String.format("T | %c | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.getPriority());
    }

    /**
     * String representation of this To Do as a task.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[T][%c] %s (%s priority)",
                this.getDoneSymbol(),
                this.description,
                this.getPriority());
    }
}