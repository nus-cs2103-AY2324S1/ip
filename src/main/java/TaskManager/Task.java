package taskmanager;

/**
 * Abstract class for few types of tasks.
 */
public abstract class Task {

    private static int count = 0; //keep track of the number of tasks
    protected boolean isDone; //completion status of the task

    /**
     * Constructs a task with an initial status of not done.
     */
    public Task() {
        this.isDone = false;
    }

    /**
     * Returns a string format of the task that can be written to the .txt file.
     *
     * @return A string format of the task to store it into the .txt file.
     */
    public abstract String toFileString();

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return the completion status of the task
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Matches the keyword to task description for query.
     */
    public abstract boolean isMatch(String keyword);

}
