package moss;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;


    /**
     * Constructs a Moss.Task object with the given description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is done, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a status icon representing the completion status of the task.
     *
     * @return An "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public String markDone() {
        this.isDone = true;
        String output = "";
        output += "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + this + "\n"
                + "____________________________________________________________\n";
        return output;
    }

    /**
     * Marks the task as not done and prints a confirmation message.
     */
    public String markUndone() {
        this.isDone = false;
        String output = "";
        output += "____________________________________________________________\n"
                + "Nice! I've marked this task as undone:\n"
                + this + "\n"
                + "____________________________________________________________\n";
        return output;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return The formatted string.
     */
    public String toString() {
        return getStatusIcon() + " | " + this.description;
    }

    public String toString(String x) {
        return getStatusIcon() + " | " + this.description;
    }
}

