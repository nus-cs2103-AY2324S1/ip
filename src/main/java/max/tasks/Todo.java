package max.tasks;

/**
 * Represents todo object.
 */
public class Todo extends Task {
    /**
     * Initialises deadline object with a description and done status.
     *
     * @param item Description of task
     */
    public Todo(String item) {
        super(item);
    }
    /**
     * Initialises deadline object with a description and done status.
     * Used when loading task from storage.
     *
     * @param item Description of task
     * @param done Status
     */
    public Todo(String item, boolean done) {
        super(item, done);
    }
    /**
     * Returns string representation of Todo task in app.
     *
     * @return String string representation of Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns string representation of Todo task in saved file.
     *
     * @return String string representation of Todo task
     */
    @Override
    public String saveItem() {
        return "T | " + super.saveItem() + "\n";
    }
}
