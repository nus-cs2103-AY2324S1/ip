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

    /**
     * Constructs a Todo object with the specified description and mark bool.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description, Boolean bool) {
        super(description, bool);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task's description and completion status for saving.
     *
     * @return A formatted string containing completion status and description.
     */
    @Override
    public String getSaveDescription() {
        String tmp = "T " + super.getSaveDescription() + "\n";
        return tmp;
    }
}
