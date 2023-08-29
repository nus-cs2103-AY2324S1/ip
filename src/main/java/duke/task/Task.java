package duke.task;

public abstract class Task {
    protected String name;
    protected boolean isComplete;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public void taskDone() {
        this.isComplete = true;
    }

    public void taskUndone() {
        this.isComplete = false;
    }

    public boolean getComplete() {
        return isComplete;
    }

    public String toSave() {
        return "";
    }

    @Override
    public String toString() {
        return isComplete ? "[X] " + name : "[ ] " + name;
    }
}
