package duke.tasks;

/**
 * Parent class of all the Tasks type.
 */
public class Task {
    /**
     * description contains the name of the task
     *
     * isDone contains whether the task is done
     */
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description The name of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task class.
     *
     * @param description The name of the Task.
     * @param isDone If task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the icon that represents the isDone state.
     *
     * @return "X" for done or " " for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of the Task, along with the
     * indication of whether it isDone.
     *
     * @return String representation of the Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the String to be written into the .txt file for saving
     * of the file.
     *
     * @return String to be written into the save file.
     */
    public String write() {
        String complete = isDone ? "1" : "0";
        return complete + " | " + this.description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
}