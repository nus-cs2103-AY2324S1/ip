package duke.tasks;

public class Task {

    /** Description of task */
    protected String description;
    /** Status of task */
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object.
     *
     * @param description Description of the Task.
     * @param isDone Status of the Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns status of task as String.
     *
     * @return Status of the Task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return status of task as Integer.
     *
     * @return Status of the Task.
     */
    public int getStatusNumerical() {
        return (this.isDone ? 1 : 0);
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns text representation of task for data file.
     *
     * @return Text representation of Task.
     */
    public String getTextRepresentation() {
        return this.getStatusNumerical() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Task)) {
            return false;
        } else {
            Task task = (Task) o;
            return task.description.equals(this.description)
                    && task.isDone == this.isDone;
        }
    }
}
