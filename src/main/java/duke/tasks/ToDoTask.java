package duke.tasks;

/**
 * Represents a to-do task in the Duke application, which is a type of task without a specific deadline.
 */
public class ToDoTask extends Task {
    /**
     * Constructs a ToDoTask with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Overrides the getDescription method to format the to-do task description with a "[T]" prefix.
     *
     * @return The formatted to-do task description.
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }

    /**
     * Overrides the toFileString method to format the to-do task description for saving to file.
     *
     * @return The formatted to-do task description for saving to file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
