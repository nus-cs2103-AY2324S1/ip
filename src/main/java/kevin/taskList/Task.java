package kevin.taskList;

/**
 * A class to represent the Task.
 */
public class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Constructor to initialize Task.
     * @param isDone This is a boolean to mark whether the Task is done or not.
     * @param name This is the name description of the task.
     */
    public Task(Boolean isDone, String name) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets isDone based on the boolean passed.
     * @param isDone This is a boolean to store to isDone.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Changes isDone to true.
     */
    public void mark() {
        this.setIsDone(true);
    }

    /**
     * Changes isDone to false.
     */
    public void unmark() {
        this.setIsDone(false);
    }

    /**
     * Returns string representation of the isDone status.
     */
    public String isDone() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the Task Object to be written to the file.
     */
    public String toText() {
        return "Task - "  + isDone + " - " + name + System.lineSeparator();
    }

    /**
     * {@inheritDoc}
     * @return Returns a string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.isDone() + "] " + this.name;
    }
}
