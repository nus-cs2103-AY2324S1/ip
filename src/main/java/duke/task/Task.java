package duke.task;

/**
 * Task class
 */
public abstract class Task {
    private String name;
    private boolean isMarked = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks the task
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Gets the name of the task
     * 
     * @return the name of the to do
     */
    public String getName() {
        return name;
    }

    /**
     * Gets whether the task is marked
     * 
     * @return whether the task is marked
     */
    public boolean getMark() {
        return isMarked;
    }

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") +"] " + name;
    }

    /**
     * Gets string format of the task in the storage
     * 
     * @return the string format of the task in the storage
     */
    public abstract String storeInString();
}
