package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns StatusIcon as a String depending on whether it's isDone value.
     *
     * @return StatusIcon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Changes the boolean value of isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Changes the boolean value of isDone to false.
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Formats the task into a String to be written into the data file.
     *
     * @return Formatted String Representation of the task.
     */
    public abstract String writeToFile();
}
