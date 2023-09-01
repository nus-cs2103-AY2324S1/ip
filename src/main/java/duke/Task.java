package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a new Task object
     * @param description description of the task
     * @param isDone whether the task has been completed or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the task
     * @return [X] if the object has been completed, [ ] otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Gets the description of the task
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to mark a task as done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Method to mark a task as not done
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * @return toString of a task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
