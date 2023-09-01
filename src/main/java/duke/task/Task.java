package duke.task;
/**
 * Task class
 */
public abstract class Task {
    // Attribute
    private String name;
    private boolean isMarked = false;

    // Constructor
    
    /**
     * The constructor of Task class
     * 
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    // Methods

    /**
     * Method to mark the task
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Method to unmark the task
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Method to get the name of the task
     * 
     * @return the name of the to do
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get whether the task is marked
     * 
     * @return whether the task is marked
     */
    public boolean getMark() {
        return isMarked;
    }

    /**
     * Method to return the string implementation of Task
     * 
     * @return the string implementation of Task
     */
    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") +"] " + name;
    }

    /**
     * Method to return the string format of the task in the storage
     * 
     * @return the string format of the task in the storage
     */
    public abstract String storeInString();
}
