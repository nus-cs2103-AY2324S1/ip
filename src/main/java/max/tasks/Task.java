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
        this.isDone = false;
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
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    /**
     * String representation of Task.
     *
     * @return String
     */
    @Override
    public String toString() {
        String doneStatus = this.isDone ? "X" : " ";
        return "[" + doneStatus + "] " + item;
    }

    /**
     * Saves task as a string in memory.
     * @return String
     */
    public String saveItem() {
        String number = isDone ? "1" : "0";
        return number + " | " + item;
    }
}