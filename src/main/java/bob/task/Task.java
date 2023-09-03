package bob.task;

/**
 * The Task class encapsulates a task in real life. A task contains a description and keeps
 * track of whether the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task Class.
     * Instantiates an instance of a task that has not been completed.
     *
     * @param description Text description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of the Task Class.
     * Instantiates an instance of a task based on provided description and
     * sets the completion status of the class based on provided boolean.
     *
     * @param description Text description of task
     * @param isDone Completions status of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Prints an icon to signify if a task is completed.
     *
     * @return X is task is completed, empty space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as yet to be completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Indicates whether the task description contains keyword provided.
     *
     * @param keyword Keyword used to check description
     * @return True if task contains keyword, false otherwise
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String convertToFileFormat() {
        return String.format("%s|%s", this.isDone ? 1 : 0, this.description);
    }
}


