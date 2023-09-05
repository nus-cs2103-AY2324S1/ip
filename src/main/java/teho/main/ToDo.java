package teho.main;

import teho.main.Task;

/**
 * Represents a task with only a description.
 */
public class ToDo extends Task {
    /**
     * Constructs new ToDo task with description.
     *
     * @param description Description of task to be done.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task details.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + this.description;
    }

    /**
     * Returns a string representation of the task details for saving task in file.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String fileString() {
        String digitStatus = this.isDone? "1": "0";
        return "T|" + digitStatus + "|" + this.description;
    }
}
