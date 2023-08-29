package duke.tasks;

/**
 * The Abstract duke.tasks.Task class containing tasks
 * of three types: duke.tasks.Event, duke.tasks.Deadline and duke.tasks.Todo
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

    /** The overloaded constructor
     * @param action The description of the task
     * @param status The status of completion of the task
     * **/
    public Task(String action, String status) {
        this.action = action;
        this.completed = status.equals("X");
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.action;
    }

    public String toFile() {
        return " | " + (this.completed ? "X" : "O") + " | " + this.action;
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

    public boolean contains(String name) {
        return this.action.contains(name);
    }
}
