package services.tasklist.tasks;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String encode() {
        return "T | " + (isDone ? "1" : "0") + " | " + showAllTags() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
