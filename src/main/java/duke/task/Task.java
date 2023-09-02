package duke.task;

/** Class to represent the task set by user*/
public class Task {
    /** Description of the task*/
    protected String description;
    /** Track whether the task is done or not*/
    protected boolean isDone;

    /** Constructor for the class*/
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
    public String printTask() {
        return this.getStatusIcon() + this.description;
    }

    public String getTypeIcon() {
        return ("Tk");
    }

    public String getDescription() {
        return this.description;
    }

}
