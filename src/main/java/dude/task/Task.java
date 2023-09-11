package dude.task;

public class Task {
    private String description;
    private boolean done;

    Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getType() {
        return "task";
    }
    public String saveTask () {return this.description; }

    @Override
    public String toString() {
        String doneStatus = "[ ]";
        if (this.isDone()) {
            doneStatus = "[X]";
        }
        return doneStatus + " " + this.description;
    }
}
