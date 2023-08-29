package duke.taskmanagement;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public String saveToFileString() {
        return getStatusIcon() + " | " + this.isDone + " | " + this.description;
    }

    public boolean contains(String str) {
        return this.description.contains(str);
    }
}
