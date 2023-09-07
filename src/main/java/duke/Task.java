package duke;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String get() {
        return description; // mark done task with X
    }

    public void set() {
        this.isDone = true; // mark done task with X
    }

    public void unset() {
        this.isDone = false; // mark done task with X
    }

    public int isDoneInt() {
        return this.isDone ? 1 : 0;
    }


    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.get();
    }

    public String toSaveString() {
        String divider = " | ";
        return this.isDoneInt() + divider + this.description;
    }


    //...
}