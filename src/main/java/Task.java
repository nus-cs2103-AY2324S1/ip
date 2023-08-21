/**
 * A Task is an object with a name and toggleable status
 */
public class Task {
    /**
     * Name of task
     */
    protected String name;
    /**
     * Whether the task is done
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with description
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns icon indicating task's status
     * @return "X" if task is done, " " otherwise
     */
    public String getStatusIcon() {
        return this.isDone? "X" : " ";
    }

    /**
     * Returns task's name
     * @return The name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of task
     * @return String representation of task containing task status and task description
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getName());
    }
}
