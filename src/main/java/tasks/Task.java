package tasks;

public class Task {
    private String description;
    private boolean isDone;

    /*
     * Constructor for the Task object.
     *
     * @params description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * Checks if the task is done and returns a Stirng
     *
     * @return String showing if the task is done or not.
     */
    public String checkDone() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /*
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return this.checkDone() + " " + this.description;
    }


    /*
     * Returns String representation of the task.
     *
     * @return String representation of the task.
     */
    public String toStringWithDateTime() {
        return this.toString();
    }

    /*
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /*
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }
}
