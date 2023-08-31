package duke.task;

/**
 * Task is an abstraction of a task. It contains a description and
 * a boolean to mark whether it has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description A String representation of the description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an X if the task is marked done, and a whitespace if not done.
     *
     * @return A String depending on the isDone attribute.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as done by setting the isDone attribute to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done by setting the isDone attribute to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of the Task that is writeable to the data file.
     *
     * @return A String object that has a correct format to be written to data file.
     */
    public String save() {
        return (this.isDone ? "Y" : "N") + "|" + this.description;
    }

    /**
     * Returns a String representation of the Task Object.
     *
     * @return A String representation of the Task Object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }



}
