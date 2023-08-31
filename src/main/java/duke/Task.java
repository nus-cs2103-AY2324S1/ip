package duke;

abstract public class Task {
    protected String task;
    protected Boolean isCompleted = false;
    public Task(String task) {
        this.task = task;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    abstract public String saveString();

    public String toString() {
        String box = this.isCompleted ? "[X]": "[ ]";
        return box + " " + this.task;
    }
}
