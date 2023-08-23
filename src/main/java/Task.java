/** Class to represent the task set by user*/
public class Task {
    /** Description of the task*/
    protected String description;
    /** Track whether the task is done or not*/
    protected boolean isDone;

    /** Cosntructor for the class*/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Method to mark the task as done*/
    public void markAsDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" +this.printTask());
    }

    /** Method to mark the task as undone*/
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + this.printTask());
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
        return ("[Tk]");
    }

}
