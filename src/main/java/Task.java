/**
 * Task class
 */
public class Task {
    // Attribute
    private String name;
    private boolean mark = false;

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
        this.mark = true;
    }

    /**
     * Method to unmark the task
     */
    public void unmark() {
        this.mark = false;
    }

    /**
     * Method to return the string implementation of Task
     * 
     * @return the string implementation of Task
     */
    @Override
    public String toString() {
        return "[" + (this.mark ? "X" : " ") +"] " + this.name;
    }
}
