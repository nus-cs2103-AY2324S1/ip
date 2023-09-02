package chatbuddy.task;

/**
 * ToDo represents a ToDo object in ChatBuddy.
 * A todo object is a task and has a description and a boolean representing whether the task is done.
 */
public class ToDo extends Task {

    /**
     * Creates an instance of a ToDo object with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns todo task information in format for saving.
     * The format is T | [1 if completed, 0 if not completed] | [task description].
     *
     * @return The todo task information in format for saving.
     */
    @Override
    public String getInformationForSaving() {
        return "T | " + super.getInformationForSaving();
    }
}
