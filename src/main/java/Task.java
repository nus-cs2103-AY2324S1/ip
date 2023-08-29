/**
 * Class that represents a task added by the user.
 */
public abstract class Task {
    /**
     * Description of task.
     */
    protected String description;

    /**
     * Boolean that represents whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor used to create a task.
     * @param description Description of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a Task.
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    public static Task fromSaveFormat(String line) throws GrumpyGordonException {
        return Parser.parseStringToTask(line);
    }

    public abstract String toSaveFormat();
}
