/**
 * A task.
 */
public class Task {
    private boolean isDone;
    private String description;

    /**
     * Creates a task instance.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns the current status of the task.
     *
     * @return A boolean value to indicate if the task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "/" : " ");
    }

    /**
     * Marks a task as done.
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
     * Marks a task as done after reading from a file.
     */
    public void markDoneFromFile() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
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
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    public String toStringInFile() {
        if (isDone) {
            return " 1 / " + this.description;
        } else {
            return " 0 / " + this.description;
        }
    }
}
