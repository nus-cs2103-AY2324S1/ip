package minion.data.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object. This is the main constructor of the minion.data.task.ToDo task.
     * @param description Description of Todo.
     * @param isDone Whether minion.data.task.ToDo is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        taskSymbol = TaskSymbol.TODO.getSymbol();
    }

    /**
     * Creates a ToDo object. This calls the main constructor when the default for isDone is false.
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
        return description.contains(query);
    }

    /**
     * Returns the string representation of the ToDo task.
     * @return string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[" + taskSymbol + "]" + super.toString();
    }

    /**
     * Returns a string representation of ToDo for storage.
     * @return string representation of ToDo for storage.
     */
    @Override
    public String toStringStorage() {
        return taskSymbol + " | " + (isDone ? "1" : "0") + " | " + description;
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
        return description.equals(t.description) && isDone == t.isDone;
    }
}
