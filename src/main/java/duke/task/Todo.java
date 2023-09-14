package duke.task;

/**
 * Represents a task that is ToDo in the Richie application
 */

public class Todo extends Task {

    /**
     * Constructor for a ToDo task
     * @param description Name or description of the ToDo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataString() {
        String doneNum = this.getIsDone() ? "1" : "0";
        return "T/" + doneNum + "/" + this.getDescription();
    }
}