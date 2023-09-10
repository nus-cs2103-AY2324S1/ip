package tasks;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {
    /**
     * Constructor for TodoTask.
     * @param description Description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructor for TodoTask.
     */
    public TodoTask() {
        super("");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo task to be saved in a file.
     * @return String
     */
    @Override
    public String toFileString() {
        return "T | " + super.getStatusIcon() + " | " + this.getDescription();
    }

    /**
     * Convert from a string to a todo task.
     * @param fileString Stored list in String format within the file
     */
    @Override
    public void fromFileString(String fileString) {
        String[] fileStringArray = fileString.split(" \\| ");
        setStatusIcon(fileStringArray[1]);
        setDescription(fileStringArray[2]);
    }
}
