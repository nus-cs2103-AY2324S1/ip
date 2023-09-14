package oscar.info;

/**
 * Task superclass that supports todo, deadline and event tasks.
 * Taken from hint.
 */
public abstract class Task extends Info {
    private boolean isDone;

    /**
     * Public constructor that is invoked by subclasses when creating task.
     *
     * @param description String description of task.
     * @param type Type of item, denoted by D for deadline, E for event and T for todo task.
     */
    public Task(String description, String type) {
        super(description, type);
        this.isDone = false;
    }

    /**
     * Checks if task is done and indicates "X" for done and blank otherwise.
     *
     * @return String indicator of task completion.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Obtains string representation of task.
     *
     * @return Information of task.
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + getStatusIcon() + "] " + super.description;
    }
}
