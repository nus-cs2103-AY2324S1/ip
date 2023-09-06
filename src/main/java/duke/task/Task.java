package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String taskDescription() {
        return (this.isDone ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }

    public String fileDescription() {
        return (this.isDone ? "1" + " | " + this.description : "0" + " | " + this.description);
    }

    public String fileString() {
        return "T | " + fileDescription();
    }

    @Override
    public String toString() {
        return "[T]" + taskDescription();
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {return this.isDone;}
}