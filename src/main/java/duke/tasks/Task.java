package duke.tasks;

public class Task {
    private String description;
    private Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + this.getDescription();
    }
}

