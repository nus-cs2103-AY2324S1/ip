package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Returns description of the task
    public String getDescription() {
        return description;
    }

    //Returns isDone status of the task
    public boolean isDone() {
        return isDone;
    }

    //Toggles isDone to True
    public void markDone() {
        this.isDone = true;
    }

    //Toggles isDone to false
    public void markNotDone() {
        this.isDone = false;
    }

    public String toFileString() {
        String type = "T"; // Default type for base Task
        return type + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
