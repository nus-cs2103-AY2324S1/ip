package echobot.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String display() {
        return "[T] " + super.display();
    }
}
