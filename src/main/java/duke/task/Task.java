package duke.task;

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String taskDescription() {
        return (this.done ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }

    public String fileDescription() {
        return (this.done ? "1" + " | " + this.description : "0" + " | " + this.description);
    }

    public String fileString() {
        return "T | " + fileDescription();
    }

    @Override
    public String toString() {
        return "[T]" + taskDescription();
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {return this.done;}
}