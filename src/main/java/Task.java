public class Task {
    protected boolean done;
    protected String name;

    /**
     * Constructor for the Task class.
     *
     * @param name The name of the task.
     * @param done Whether the task is marked done or not.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
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

    public String displayableForm() {
        return null;
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
