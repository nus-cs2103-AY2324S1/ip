package max.tasks;

/**
 * Represents a task object.
 */
public class Task {
    private String item;
    private boolean done;

    /**
     * Initialises Task object with a description and done status to be false.
     *
     * @param item Description of task
     */
    public Task(String item) {
        this.item = item;
        this.done = false;
    }

    /**
     * Initialises Task object with a description and done status.
     * Used when loading task from storage.
     *
     * @param item Description of task
     * @param done Status
     */
    public Task(String item, boolean done) {
        this.item = item;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }

    /**
     * String representation of Task.
     *
     * @return String
     */
    @Override
    public String toString() {
        String doneStatus = this.done ? "X" : " ";
        return "[" + doneStatus + "] " + item;
    }

    /**
     * Saves task as a string in memory.
     * @return String
     */
    public String saveItem() {
        String number = done ? "1" : "0";
        return number + " | " + item;
    }
}