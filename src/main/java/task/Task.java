package task;

public class Task {
    protected boolean completed;
    protected String nameOfTask;

    public Task(String nameOfTask) {
        this.completed = false;
        this.nameOfTask = nameOfTask;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + nameOfTask;
        } else {
            return "[ ] " + nameOfTask;
        }
    }

    public void setUnmarked() {
        this.completed = false;
    }

    public void setMarked() {
        this.completed = true;
    }

}
