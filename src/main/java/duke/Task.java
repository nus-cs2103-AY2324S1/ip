package duke;

/**
 * Represents a basic task with a description and completion status.
 *
 * @author Qin Yan Er
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task instance.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Description cannot be null or empty.";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string to save the task to a file.
     *
     * @return A formatted string representing the task.
     */
    public String saveTask() {
        if (this.isDone) {
            return "1" + " | " + this.description;
        } else {
            return "0" + " | " + this.description;
        }
    }

    /**
     * Loads task data from a formatted string.
     *
     * @param savedTasks A formatted string representing a saved task.
     * @return A Task object reconstructed from the saved data.
     */
    public static Task loadData(String savedTasks) {
        String[] parts = savedTasks.split(" \\| ");
        String type = parts[0];
        String doneOrNot = parts[1];
        String description = parts[2];

        Task task;
        if (doneOrNot.equals("1")) {
            if (type.equals("T")) {
                task = new Todo(description);
                task.markAsDone();
            } else if (type.equals("D")) {
                String by = parts[3];
                task = new Deadline(description, by);
                task.markAsDone();
            } else {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                task.markAsDone();
            }
        } else {
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                String by = parts[3];
                task = new Deadline(description, by);
            } else {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            }
        }
            return task;
    }


    /**
     * Converts the task to a formatted string for display.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }


}