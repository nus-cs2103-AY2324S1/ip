package ballsorting;
public class Todo extends Task {
    /**
     * Creates a new instance of Todo.
     * @param description Brief summary of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the Todo.
     * @return String summary of the Todo.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a String representation of the Todo that can be stored in Storage.
     * @return Storage String of Todo.
     */
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "T|" + stat + "|" + this.description;
    }
}
