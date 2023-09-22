package duke.task;

/**
 * Represents a duke.task.Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a duke.task.Todo instance.
     *
     * @param description The description of the underlying task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides the string representation of the duke.task.Todo instance.
     *
     * @return A string with the relevant information of the duke.task.Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a formatted string of the duke.task.Todo task to add to the save file.
     *
     * @return A formatted string with the relevant information for the save file.
     */
    @Override
    public String getSaveString() {
        return String.format("T | %d | %s",
                this.isDone ? 1 : 0,
                this.description);
    }

    /**
     * Clones this duke.task.Todo instance.
     *
     * @return The cloned instance.
     */
    @Override
    public Task clone() {
        Todo temp = new Todo(this.description);
        if (this.isDone) {
            temp.markAsDone();
        }
        return temp;
    }
}
