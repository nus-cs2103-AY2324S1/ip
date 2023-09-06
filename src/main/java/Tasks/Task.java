package Tasks;

/**
 * This class is the parent to all the types of Tasks.
 */
public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * This method gets the description of the Task.
     * @return The description of the Task.
     */
    public String getDescription() {

        return description;
    }

    /**
     * This method changes the isDone field to true.
     */
    public void markDone() {
        done = true;
    }

    /**
     * This method changes the isDone field to false.
     */
    public void markUndone() {

        done = false;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * This method returns a String which indicates whether or not the task is done.
     * @return X if the task is done, empty space otherwise.
     */
    public String getStatusIcon() {

        return (done ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {

        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
