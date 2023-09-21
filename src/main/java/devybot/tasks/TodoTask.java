package devybot.tasks;

/**
 * The TodoTask class represents a task without a specific deadline or event in
 * the DevyBot task management system.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask with a given description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Converts the task to a human-readable string representation.
     *
     * @return A human-readable string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task to a string representation suitable for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
