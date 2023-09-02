package taskmanager;

/**
 * Abstract class for few types of tasks.
 */
public abstract class Tasks {

    protected boolean isDone; //completion status of the task

    private static int count = 0; //keep track of the number of tasks

    /**
     * Returns a string format of the task that can be written to the .txt file.
     *
     * @return A string format of the task to store it into the .txt file.
     */
    public abstract String toFileString();

    /**
     * Constructs a task with an initial status of not done.
     */
    public Tasks() {
        this.isDone = false;
    }

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
     * Match the keyword to task description for query.
     */
    public abstract boolean isMatch(String keyword);

}
