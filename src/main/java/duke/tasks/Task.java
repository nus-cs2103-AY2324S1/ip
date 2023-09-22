package duke.tasks;

/**
 * Encapsulation for Task properties.
 */
public class Task {

    /** Description of task */
    protected String description;
    /** Status of task */
    protected boolean isDone;
    /** Priority of task */
    protected Priority priority;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
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
     * Constructs a Task object.
     *
     * @param description Description of the Task.
     * @param isDone Status of the Task.
     * @param priority Priority of the Task.
     */
    public Task(String description, boolean isDone, Priority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
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
     * Returns status of task as Integer.
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
     * Changes the priority of the task.
     *
     * @param priority New priority of the task.
     */
    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns text representation of task for data file.
     *
     * @return Text representation of Task.
     */
    public String getTextRepresentation() {
        return this.getStatusNumerical() + " | " + this.priority + " | " + this.description;
    }

    /**
     * Checks if task contains keyword.
     *
     * @param keyword Keyword to be checked.
     * @return If task contains keyword or not.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "][" + this.priority + "] " + this.description;
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
