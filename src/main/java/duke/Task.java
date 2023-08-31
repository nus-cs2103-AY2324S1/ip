package duke;

public class Task {

    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task() {
        this.task = " ";
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toFileString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public String toString() {
        return "[" + (this.isDone? "X" : " ") + "] " + this.task;
    }
}
