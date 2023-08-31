public class Task {
    private boolean isDone;
    private final String NAME;
    final String DONE = "[X] ";
    final String NOTDONE = "[] ";

    /**
     * Constructor for the Task class.
     *
     * @param name   The name of the task.
     * @param isDone Whether the task is marked as done or not.
     */
    Task(String name, boolean isDone) {
        this.NAME = name;
        this.isDone = isDone;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean checkDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task, including its name and status.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return this.checkDone()
                ? DONE + this.NAME
                : NOTDONE + this.NAME;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkDone() {
        isDone = false;
    }
}
