package duke.task;

/** Class to represent the task set by user*/
public abstract class Task {
    /** Description of the task*/
    protected String description;
    /** Track whether the task is done or not*/
    protected boolean isDone;

    /**
     * Constructor for the Task
     * @param description The description of the task based on the user input
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Method to mark the task as done*/
    public void markAsDone() {
        this.isDone = true;
    }

    /** Method to mark the task as undone*/
    public void markAsUndone() {
        this.isDone = false;
    }

    /** Return icon based on whether task is done or undone*/
    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /** Method to print the task*/
    public abstract String printTask();

    /** Method to get the typeIcon the task*/
    public abstract String getTypeIcon();

}
