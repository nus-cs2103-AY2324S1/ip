/**
 * This is a class representing a single task with fields to represent details and done state
 * @author Selwyn
 */
public class Task {
    /**
     * Field representing details of the task
     */
    protected String detail;

    /**
     * Field representing whether the task is done or not
     */
    protected boolean isDone;

    /**
     * Constructor for a task
     * @param detail
     */
    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    /**
     * Displays and prints the task
     */
    public void displayTask() {
        System.out.print(this.isDone ? "[X] " : "[ ]");
        System.out.println(this.detail);
    }

    /**
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }
}