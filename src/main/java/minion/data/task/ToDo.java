package minion.data.task;

/**
 * Represents a minion.data.task.ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a minion.data.task.ToDo object. This is the main constructor of the minion.data.task.ToDo task.
     * @param description Description of Todo.
     * @param isDone Whether minion.data.task.ToDo is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a minion.data.task.ToDo object. This calls the main constructor when the default for isDone is false.
     * @param description Description of minion.data.task.ToDo.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Returns whether the ToDo contains the query.
     * @param query the query.
     * @return whether the ToDo contains the query.
     */
    @Override
    public boolean contains(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns the string representation of the minion.data.task.ToDo task.
     * @return string representation of the minion.data.task.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of minion.data.task.ToDo to be used in storage.
     * @return string representation of minion.data.task.ToDo for storage.
     */
    @Override
    public String toStringStorage() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ToDo)) {
            return false;
        }
        ToDo t = (ToDo) o;
        return this.description.equals(t.description) && this.isDone == t.isDone;
    }
}
