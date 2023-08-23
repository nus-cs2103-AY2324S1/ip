public class Task {
    protected boolean done;
    protected String name;

    /**
     * Constructor for the Task class.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setNotDone() {
        this.done = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representing the task.
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }
}
