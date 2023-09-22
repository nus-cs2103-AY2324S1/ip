package duke.task;
/**
 * Todo instance of a task
 */
public class ToDo extends Task {
    /**
     * Constructs a task with a given description. Completion of the task
     * is false by default.
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string of the status of the task.
     * @return string containing completion status, type and description of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted in the way it is to be saved.
     * @return formatted string to be written into file
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.completionStatus() + " | " + this.description + " | " + priorityString();
    }
}
