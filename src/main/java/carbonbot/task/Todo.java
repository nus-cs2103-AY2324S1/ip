package carbonbot.task;

/**
 * Represents task without any date/time attached to it
 */
public class Todo extends Task {
    private static final String TASK_ICON = "[T]";

    /**
     * Constructs a Todo task with the provided description.
     *
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String serialize() {
        return String.format("T | %d | %s",
                super.isDone ? 1 : 0,
                super.description);
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString();
    }
}
