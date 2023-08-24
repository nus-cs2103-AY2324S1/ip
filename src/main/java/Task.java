public class Task {
    private boolean isDone;
    private String description;

    /**
     * Create a task instance.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "/" : " ");
    }

    /**
     * Mark a task as done.
     */
    public void markDone() {
        if (this.isDone) {
            System.out.println("It has already been done");
        } else {
            this.isDone = true;
            System.out.println("I've marked this task as done!\n" + this);
        }
    }

    /**
     * Mark a task as undone.
     */
    public void markUndone() {
        if (!this.isDone) {
            System.out.println("It was already undone");
        } else {
            this.isDone = false;
            System.out.println("I've marked this task as undone!\n" + this);
        }
    }

    /**
     * String representation of the task.
     * @return Desired representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
