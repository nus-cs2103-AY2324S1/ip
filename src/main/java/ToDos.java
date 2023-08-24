/**
 * A class for ToDos tasks.
 */
public class ToDos extends Task {
    /**
     * Constructor to initialize the ToDos class.
     * @param description Describes the todos task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Method that overrides default toString.
     * @return String representation of ToDos.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}