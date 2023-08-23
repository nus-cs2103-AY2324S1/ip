/**
 * The Abstract Task class containing tasks
 * of three types: Event, Deadline and Todo
 * @author: Shishir
 **/
abstract public class Task {

    /** The description of the task. **/
    private String action;

    /** The completion status of the task. **/
    private boolean completed;

    /** The constructor
     * @param action The description of the task.
     * **/
    public Task(String action) {
        this.action = action;
        this.completed = false;
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.action + " ";
    }

    /** The function to complete a task. **/
    public void completeTask() {
        this.completed = true;
    }

    /** The function to check status of a task.
     * @return The status of completion.
     **/
    public boolean isCompleted() { return this.completed; }

    /**The function to revert a task. **/
    public void revertTask() {
        this.completed = false;
    }
}
