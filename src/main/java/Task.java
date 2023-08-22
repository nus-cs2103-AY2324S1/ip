public class Task {

    /** Description of the task. */
    protected String description;
    /** State of task if completed or not. */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task if state of task is completed.
     *
     * @return "X" if completed empty string if otherwise.
     * */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Change the state of task to completed. */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
    }

    /** Change the state of task to not completed. */
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /** Informs users that task is added to list. */
    public void notice() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.toString());
    }

    /**
     * Display string representation of task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
