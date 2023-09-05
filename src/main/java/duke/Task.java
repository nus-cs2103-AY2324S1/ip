package duke;

/**
 * Represents a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new <code>Task</code> object.
     * @param description the description given by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes the boolean <code>isDone</code> to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the boolean <code>isDone</code> to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task to be displayed to the user.
     * @return String representation of the task to be displayed.
     */
    public String getDescription() {
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    /**
     * Gets the description of the task to be saved to the hard disk.
     * @return String representation of the task to be saved.
     */
    public String getSavedString() {
        String status = this.isDone ? "1" : "0";
        return "| " + status + " | " + this.description;
    }

    /**
     * Checks if a search term is in the description of a task.
     * @param term the term to check for in the task description.
     * @return returns true if the term is in the task description and false if the term is not in the task description
     */
    public boolean checkTerm(String term) {
        return this.description.toLowerCase().contains(term.toLowerCase());
    }
}
