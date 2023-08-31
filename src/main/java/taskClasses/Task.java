package taskClasses;

/**
 * Abstract class representing a general task.
 * Specific types of tasks (e.g., taskClasses.ToDo, taskClasses.Deadline, taskClasses.Event) should extend this class.
 */
public abstract class Task {
    protected String description;  // Description of the task
    protected boolean isDone; // taskClasses.Task completion status
    protected String type; // Type of the task (e.g., TODO, DEADLINE, EVENT)

    /**
     * Abstract method to get additional details of a task.
     * Implementation should be provided in subclasses.
     *
     * @return A string representing additional details of the task.
     */
    public abstract String getDetails();

    public abstract String getDBString();


    /**
     * Constructor to initialize a taskClasses.Task object.
     *
     * @param description Description of the task.
     * @param type        Type of the task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Prints the description of the added task and the current task count.
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
     * Marks the task as done and prints a confirmation message.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done and prints a confirmation message.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }



    public boolean isDone() {
        return this.isDone;
    }

    public String String() {
        return this.description;
    }
}
