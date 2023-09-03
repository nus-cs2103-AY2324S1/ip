package task;

/**
 * Task is the object created to identify tasks the user adds into
 * the chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The constructor of Task.
     *
     * @param description the task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        if (isDone) {
            System.out.println("Task is already done! (≧∇≦)/");
        } else {
            this.isDone = true;
        }
    }

    /**
     * Marks the task as incomplete.
     */
    public void markUndone() {
        if (isDone) {
            this.isDone = false;
        } else {
            System.out.println("Task is not done yet! (;° ロ°)");
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
