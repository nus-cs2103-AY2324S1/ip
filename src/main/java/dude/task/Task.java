package dude.task;

public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getType() {
        return "task";
    }
    public String saveTask() {return this.description; }

    public boolean containKeywords(String keywords){
        return this.description.contains(keywords);
    }

    @Override
    public String toString() {
        String doneStatus = "[ ]";
        if (this.isDone()) {
            doneStatus = "[X]";
        }
        return doneStatus + " " + this.description;
    }
}
