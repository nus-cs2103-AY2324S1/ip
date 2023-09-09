package smolbrain.task;

/**
 * Used to create different task types
 */
public class Task {

    /** Task description */
    protected String description;
    /** Whether task is done */
    protected boolean isDone;
    /** Task priority level **/
    protected int priorityLevel = 0;

    /**
     * Creates a task with specified description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string symbol indicating if task is marked.
     *
     * @return String symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a number as a string indicating if task is marked, for saving into save file.
     *
     * @return String representation of marked number.
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks this task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks this task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Sets the priority level of this task.
     *
     * @param level Priority level for this task.
     */
    public void setPriorityLevel(int level) {
        this.priorityLevel = level;
    }

    /**
     * Checks if task description contains the keyword.
     *
     * @param keyword Keyword to check.
     * @return Whether task description contains the keyword.
     */
    public boolean contain(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Encodes this task into a string for saving into save file.
     *
     * @return Encoded string representation.
     */
    public String encode() {
        return "";
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[%s][%d] %s", getStatusIcon(), priorityLevel, description);
    }

}
