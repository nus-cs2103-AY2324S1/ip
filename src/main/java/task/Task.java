package task;

/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param description description provided by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter method which updates whether a task is done.
     *
     * @param status Boolean value which represents whether a task is done
     *               true - task is done, false - task is not done
     */
    public void setTaskDone(boolean status) {
        this.isDone = status;
    }

    /**
     * storeFormat() method.
     *
     * @return The specific format in which the task should be saved in the data file.
     */
    public String outputStoreFormat() {
        return null;
    }

    /**
     * getter function.
     *
     * @return Retrieves the saved description.
     */
    public String getDescription() {
        return this.description;
    }

    public String updateDescription(String updatedDescription) {
        String oldDescription = this.description;
        this.description = updatedDescription;
        return oldDescription;
    }

    /**
     * toString() method.
     *
     * @return String text of the task in which the task will be displayed in the terminal.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

