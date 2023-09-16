package chatty.task;

/**
 * A class that represents each task added by the user.
 */
public class Task {

    protected String task;
    protected boolean isDone;

    /**
     * Constructor for the Task class that creates a task object for each task to be added.
     *
     * @param task The task that the user wants to add.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Changes the state of the task according to the instruction given by the user.
     *
     * @param status The status of the task that the user wants to change to.
     */
    public void markStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Checks if the task contains the entered keyword.
     *
     * @param keyword The keyword that the user wants to find.
     * @return Returns a boolean value to indicate if the task contains the particular keyword.
     */
    public boolean checkKeyword(String keyword) {
        return this.task.contains(keyword);
    }

}
