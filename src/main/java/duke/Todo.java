package duke;

/**
 * Represents a task with description only.
 */
public class Todo extends Task{

    public Todo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T--" + super.toFileString();
    }
}
