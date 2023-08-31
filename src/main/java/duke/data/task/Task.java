package duke.data.task;
public class Task {
    /** Description of the task */
    protected String description;
    /** Whether the task is marked as done */
    protected boolean isDone;

    /**
     * Constructor to initialize Task.
     *
     * @param description Description of the Todo task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks task as done by setting isDone to true */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks task as undone by setting isDone to false */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Checks whether task is done
     *
     * @return boolean of whether the task is done or not
     * */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Gets the status icon of the task
     *
     * @return X if task is marked as done and empty if not done
     * */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the formatted string of the task to be written to .txt file
     *
     * @return formatted string of the task
     * */
    public String toWrite() {
        if (isDone)
            return "1 | " + description;
        return "0 | " + description;

    }

    /**
     * Gets the formatted string of the task to be displayed in the UI
     *
     * @return formatted string of the task
     * */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
