package chatterbot.data;

/**
 * Represents the tasks that are added to the task list.
 */
public class Task {
    private String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        assert !description.isEmpty() : "Description cannot be blank.";
        this.isDone = false;
    }

    /**
     * Returns the status of the task (whether it is done or undone).
     * @return String This shows the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method marks the task as done, denoted by [X].
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * This method marks the task as undone, denoted by [ ].
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return String This is the task description in the format it will be displayed in, in the list.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the input for the Chatterbot.txt file in the same format as was entered by the user.
     * @return String This is the formatted line to add to the ChatterBot.txt file.
     */
    public String formatForFile() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }

    public boolean contains(String query) {
        return description.contains(query);
    }
}