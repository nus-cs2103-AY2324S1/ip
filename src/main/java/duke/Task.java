package duke;

/**
 * Represents the items that need to be completed by the user.
 */
public class Task {

    protected Tag tag;
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructs a Task with a short description of the task at hand.
     * It starts of with it not being completed as how it should be.
     *
     * @param description A name/short summary of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns the status of a task.
     * If X is returned, the task is completed.
     * If it is empty, the task is incomplete.
     *
     * @return The icon to indicate if the task is complete or not.
     */
    public String getStatusIcon() {
        return (isCompleted? "X" : " ");
    }


    /**
     * Marks the task as complete.
     * Toggles the isCompleted field to true.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     * Toggles the isCompleted field to false.
     */
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return description;
    }

    public Tag getTag() {
        return this.tag;
    }
}
