package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representation of whether the task us marked as done.
     * @return a String representation of whether the task us marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Edits the status of the Task.
     * @param status Boolean value that represents whether the task is marked as done or undone.
     * @return The Task that has been marked or unmarked as done.
     */
    public Task description(boolean status) {
        isDone = status;
        return this;
    }


    /**
     * Returns the String representation of the Task that is to be stored in the specified file.
     * @return The String representation of the Task that is to be stored in the specified file.
     */
    public String toWrite() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the String representation of the Task that is printed.
     * @return The String representation of the Task that is printed.
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
