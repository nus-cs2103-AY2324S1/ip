package duke.tasks;

/**
 * A task object that tracks Todos.
 */
public class Todo extends Task {
    /**
     * Public constructor for Todo.
     *
     * @param description of the todo object
     * @param isMarked boolean value if the Event task is marked
     */
    public Todo(String description, boolean isMarked) {
        super(description, "todo", isMarked);
    }

    @Override
    public String getOriginalMessage() {
        return "todo " + this.getDescription();
    }
}

