/**
 * A simple task only holding the description.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo task
     *
     * @param description The description of the task.
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns a string representation of task
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return this.isDone ? "[T][X] "+this.description : "[T][ ] "+this.description ;
    }
}
