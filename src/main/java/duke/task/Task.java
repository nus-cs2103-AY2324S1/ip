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
        assert !isDone : "The task is already done!";
        isDone = true;
    }

    /**
     * Changes the boolean value of isDone to false.
     */
    public void markAsUndone() {
        assert isDone : "The task is still undone!";
        isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Finds the target String within the description of the current task.
     *
     * @param target The String to be found.
     * @return True or False depending on whether the target is found.
     */
    public boolean find(String target) {
        return this.description.contains(target);
    }

    /**
     * Formats the task into a String to be written into the data file.
     *
     * @return Formatted String Representation of the task.
     */
    public abstract String writeToFile();
}
