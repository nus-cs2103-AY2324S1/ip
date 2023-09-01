package extensions;
/**
 * Represents a task with no additional attributes.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getSaveFormat() {
        return String.format("T | %c | %s",
                this.getDoneSymbol(),
                this.description);
    }
    @Override
    public String toString() {
        return String.format("[T][%c] %s",
                this.getDoneSymbol(),
                this.description);
    }
}