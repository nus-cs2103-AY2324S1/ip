package duke.task;
/**
 * The Task class is the base class for creating task objects in Duke. Task objects
 * can be of different types, including ToDos, Deadlines, and Events.
 */
public abstract class Task{
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to build a Task Object with the task description.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Overloaded constructor to build a Task Object with the task description and completion status.
     *
     * @param description The description of the Task.
     * @param isDone      Represents the completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Returns a formatted string representation of the Task, including its completion
     * status and description.
     *
     * @return A string representing the Task.
     */
    public String taskString() {
        String str = this.isDone ? "[X] " : "[ ] ";
        String output = str + this.description;
        return output;
    }

    /**
     * Abstract method for saving the task to a text file. Subclasses will override
     * and implement this method.
     *
     * @return A string suitable for saving the Task to a text file.
     */
    public abstract String saveTaskString();
}