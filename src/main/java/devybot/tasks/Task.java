package devybot.tasks;

/**
 * The Task class represents a generic task in the DevyBot task management
 * system.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean checkDone() {
        return isDone;
    }

    /**
     * Checks if the task description matches a given keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return True if the task description matches the keyword, false otherwise.
     */
    public boolean isMatching(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Checks if the task description contains a given keyword (case-insensitive).
     *
     * @param keyword The keyword to search for in the task description.
     * @return True if the task description contains the keyword (case-insensitive),
     *         false otherwise.
     */
    public boolean isContaining(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Converts the task to a string representation suitable for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return status + " | " + description;
    }

    /**
     * Converts the task to a human-readable string representation.
     *
     * @return A human-readable string representation of the task.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + description;
    }
}
