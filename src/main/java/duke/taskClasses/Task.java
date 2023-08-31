package duke.taskClasses;

/**
 * Abstract class representing a general task.
 * Specific types of tasks (e.g., ToDo, Deadline, Event) should extend this class.
 */
public abstract class Task {

    /** Description of the task */
    protected String description;

    /** Task completion status */
    protected boolean isDone;

    /** Type of the task (e.g., TODO, DEADLINE, EVENT) */
    protected String type;

    /**
     * Constructor to initialize a Task object.
     *
     * @param description Description of the task.
     * @param type Type of the task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Abstract method to get additional details of a task.
     * Implementation should be provided in subclasses.
     *
     * @return A string representing additional details of the task.
     */
    public abstract String getDetails();

    /**
     * Abstract method to get the database string representation of a task.
     * Implementation should be provided in subclasses.
     *
     * @return A string suitable for database storage.
     */
    public abstract String getDBString();

    /**
     * Prints the description of the added task.
     */
    public void addedTaskDescription() {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + this.getStatusAndDescription());
    }

    /**
     * Returns the status icon based on task completion.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the status icon, task type, description, and additional details.
     *
     * @return A formatted string representing the task.
     */
    public String getStatusAndDescription() {
        return String.format("[%s][%s] %s%s",
                this.getType(),
                this.getStatusIcon(),
                this.description,
                this.getDetails());
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the task's description.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
