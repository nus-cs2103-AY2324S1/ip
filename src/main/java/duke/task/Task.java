package duke.task;

public class Task {

    /*
        The detail of the dask.
     */
    protected String description;
    /*
        The status of the task whether it is completed or not.
     */
    protected boolean isDone;

    /**
     * Constructs an Event object with the provided description and status.
     *
     * @param description The description of the task.
     * @param isDone True if the task is done else otherwise.
     */
    public Task(String description, boolean isDone) {

        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the status of the task.
     *
     * @return A "X" if the task is completed else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set the task to complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Set the task to incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Generates the format of the task to be written into the text file.
     *
     * @return The format of the task in the text file.
     */
    public String contentLine() {
        return "/" + getStatusIcon() + "/" + this.description;
    }

    /**
     * Overrides the 'toString' method of the parent class with to display the task in different format.
     *
     * @return The appearance of the task in the application.
     */
    @Override
    public String toString() {

        String result = "[" + getStatusIcon() + "] " + this.description;
        return result;
    }
}


