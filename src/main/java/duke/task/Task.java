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


    public Task description(boolean status) {
        isDone = status;
        return this;
    }


    public String toWrite() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
