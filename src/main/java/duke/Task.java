package duke;

abstract public class Task {
    protected String description;
    protected Boolean isCompleted = false;
    public Task(String description) {
        this.description = description;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    abstract public String saveString();

    public String toString() {
        String box = this.isCompleted ? "[X]": "[ ]";
        return box + " " + this.description;
    }
}
