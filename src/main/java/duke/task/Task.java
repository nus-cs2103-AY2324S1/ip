package duke.task;

/**
 * The abstract Task class is to encapsulate the task into a single object
 * It will contain the description and whether it has been completed
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description the task description.
     */
    public Task(String description, String done) {
        this.description = description;
        this.isDone = done.equals("1");
    }

    /**
     * Constructor for Task where isDone is default false.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks isDone status.
     * @return Boolean value of whether the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String fileRepresentation();
}
