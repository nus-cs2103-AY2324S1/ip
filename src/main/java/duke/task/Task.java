package duke.task;

/**
 * Represents task that user is keeping track of.
 */
public class Task {
    private String description;
    private boolean done;

    /**
     * Constructor for the class Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns the checkbox component of the task when called as a String
     * to show if task has been completed.
     * @return The checkbox component of the String of the task.
     */
    public String taskDescription() {
        return (this.done ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }

    /**
     * Returns if task has been completed in the appropriate format to be saved in the file.
     * @return
     */
    public String fileDescription() {
        return (this.done ? "1" + " | " + this.description : "0" + " | " + this.description);
    }

    /**
     * Returns type of task and its description in the appropriate format to be saved in the file.
     * @return Type of file and its description.
     */
    public String fileString() {
        return "T | " + fileDescription();
    }

    /**
     * Returns type of file and its description when called as a String.
     * @return Type of file and its description.
     */
    @Override
    public String toString() {
        return "[T]" + taskDescription();
    }

    /**
     * Setter for done.
     * @param done Sets task as done if true, not done if false.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Getter for done.
     * @return The state of the task if it has been done.
     */
    public boolean isDone() {return this.done;}
}