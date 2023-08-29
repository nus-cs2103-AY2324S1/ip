package main.java;
/**
 * Represents a todo task, which is a task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, Boolean bool) {
        super(description, bool);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveDescription() {
        String tmp = "T " + super.getSaveDescription() + "\n";
        return tmp;
    }
}
