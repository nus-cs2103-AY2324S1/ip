package mattbot.task;

/**
 * Skeleton for Tasks. Deadlines, Events and Todos are examples of such tasks.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructor for a primitive Task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the current Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String of the current status of the Task.
     *
     * @return String "X" if the Task is done, " " otherwise.
     */
    public String showStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a String with the name of the object.
     *
     * @return String with name of the object.
     */
    public String showName() {
        return this.name;
    }
    /**
     * Identifies itself as an Event.
     *
     * @returns Character to identify the type of Task.
     */
    public abstract String identifier();

    /**
     * Returns String form for storage.
     *
     * @return String for storage format.
     */
    public abstract String toFile();
    /**
     * Returns a String describing the doneness of a Task as a String.
     *
     * @return String "1" if task is complete, "0" otherwise.
     */
    public String showStatusAsFile() { return (isDone ? "1" : "0"); }
    public String toString() {
        return String.format("[%s] [%s] %s", this.identifier(), this.showStatus(), this.showName());
    }
}
