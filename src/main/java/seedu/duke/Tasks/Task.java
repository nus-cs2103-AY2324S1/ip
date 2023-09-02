package seedu.duke.Tasks;

public class Task {
    /**
     * Taken from the Partial Solution given on https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
     * A class of duke.Tasks to create tasks that need to be in list of tasks.
     */
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object with a description and marked status.
     *
     * @param description String description of the task.
     * @param isDone      Boolean indicating whether the task is done (true) or undone (false).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon representing the completion status of the task.
     *
     * @return String which is a status icon ("X" if done, " " if undone).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a formatted string representation of the task.
     * The format has the status icon and the task's description.
     *
     * @return String that represents the task and its completion status.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a formatted string representation of the task's status for data storage.
     * The format has a 1 for done and a 0 for undone and the task's description.
     *
     * @return String that represents the task to be stored in data file.
     */
    public String addDataFormat() {
        if (this.isDone) {
            return "| 1 | " + this.description;
        } else {
            return "| 0 | " + this.description;
        }
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}