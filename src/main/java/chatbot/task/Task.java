package chatbot.task;

/**
 * Abstract class representing a task object.
 * 
 * @author Owen Yeo
 * @version Level-7
 */
public abstract class Task {
    private String label;
    private boolean isDone;

    /**
     * Constructor for a task object
     * 
     * @param label
     */
    Task(String label) {
        this.label = label;
        this.isDone = false;
    }

    /**
     * sets isDone to true.
     */
    public void done() {
        isDone = true;
    }

    /**
     * sets isDone to false.
     */
    public void undone() {
        isDone = false;
    }

    /**
     * Returns a string to be saved in a file, representing a task.
     * 
     * @return String representing a task.
     */
    public String toSaveString() {
        return "| " + (isDone ? 1 : 0) + " | " + label;
    }

    /**
     * {@inheritDoc}
     * 
     * Represents whether a task has been done or not.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + label;
        }
        return "[ ] " + label;
    }
}
