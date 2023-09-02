package chatbot.task;

public class Task {
    /** The description of the task. */
    protected String description;

    /** The done status of the task. */
    protected boolean isDone;

    /** Constructor for a task.
     *
     * @param description The task description.
     * */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns an X if the task is done.
     *
     * @return A string showing if the task has been done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Prints out task done status along with the task description.
     *
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /** Marks the task done status with the boolean argument.
     *
     * @param status The boolean value to represent the task done status with.
     * @return Returns true if successful.
     */
    public boolean markStatus(boolean status) {
        this.isDone = status;
        return true;
    }
}
