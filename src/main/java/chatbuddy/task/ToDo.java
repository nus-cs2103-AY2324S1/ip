package chatbuddy.task;

public class ToDo extends Task {

    /**
     * Constructor to create a chatbuddy.task.ToDo object.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method to get the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns todo task information in format for saving.
     * Format is T | [1 if completed, 0 if not completed] | [task description]
     *
     * @return Todo task information in format for saving
     */
    @Override
    public String getInformationForSaving() {
        return "T | " + super.getInformationForSaving();
    }
}
