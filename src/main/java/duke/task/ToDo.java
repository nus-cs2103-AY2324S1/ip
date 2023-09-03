package duke.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Initialises the todo task with the given description.
     * 
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toFileString() {
        return super.toFileString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo todo = (ToDo) obj;
            return this.description.equals(todo.description);
        }
        return false;
    }
}