package duke.task;

/**
 * Task class
 */
public abstract class Task {
    private String name;
    private boolean mark = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task
     */
    public void mark() {
        this.mark = true;
    }

    /**
     * Unmarks the task
     */
    public void unmark() {
        this.mark = false;
    }

    /**
     * Gets the name of the task
     * 
     * @return the name of the to do
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets whether the task is marked
     * 
     * @return whether the task is marked
     */
    public boolean getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "[" + (this.mark ? "X" : " ") +"] " + this.name;
    }

    /**
     * Gets string format of the task in the storage
     * 
     * @return the string format of the task in the storage
     */
    public abstract String storeInString();
}
