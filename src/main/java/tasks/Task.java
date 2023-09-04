package tasks;

/**
 * This class encapsulates an abstract Parent class Task
 * that users can create to add to the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Constructs a Task object that specifies whether it has been completed.
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public void setIsNotDone() {
        this.isDone = false;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String representation of whether the Task has been completed,
     * indicated by an "X" for completion.
     *
     * @return String representation of completion status.
     */
    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the String representation of the Task object when saved to the Duke.txt file.
     *
     * @return String representation of Task object.
     */
    public abstract String toText();

    public String toString() {
        return this.getStatus() + " " + this.description;
    }
}
