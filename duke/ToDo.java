package duke;

/**
 * The ToDo class represents a task with a description but no specific date and time associated with it.
 * It extends the Task class and is used to represent tasks that do not have a deadline or event time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its status icon and description.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return " " + "[T]" + status + this.description;
    }
}

