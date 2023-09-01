package tasks;

/**
 * Represents a task object that will be used to keep track in
 * the chatbot.
 */
public class Task {

    private final String desc;
    private boolean isCompleted;

    /**
     * Constructor for Task.
     * @param desc Description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isCompleted = false;
    }

    /**
     * Marks the task completed.
     */
    public void taskCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task not completed.
     */
    public void taskNotCompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns the marked status of the task.
     * @return Marked status.
     */
    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    /**
     * Returns the description of the task.
     * @return Task's description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the string representation of task.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "]" + " " + this.desc;
    }
}
