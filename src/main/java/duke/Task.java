package duke;

abstract public class Task {
    protected String task;
    protected Boolean completed = false;
    public Task(String task) {
        this.task = task;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    abstract public String saveString();

    public String toString() {
        String box = this.completed ? "[X]": "[ ]";
        return box + " " + this.task;
    }
}
