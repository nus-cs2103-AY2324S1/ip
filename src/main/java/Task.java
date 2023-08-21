/**
 * Represents a task in the task list.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println(this);
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println(this);;
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
