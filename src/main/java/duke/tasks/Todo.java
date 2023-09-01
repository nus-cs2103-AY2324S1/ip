package duke.tasks;

/**
 * A task object that tracks Todos.
 */
public class Todo extends Task {
    public Todo(String description, boolean isMarked) {
        super(description, "todo", isMarked);
    }

    @Override
    public String getOriginalMessage() {
        return "todo " + this.getDescription();
    }
}

