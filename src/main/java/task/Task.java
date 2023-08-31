package task;

abstract public class Task {
    protected boolean completed;
    protected String nameOfTask;

    public Task(boolean completed, String nameOfTask) {
        this.completed = completed;
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

    abstract public String typeOfString();
    public String statusString() {
        if (completed) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

    abstract public String taskDetailsString();

    public void setUnmarked() {
        this.completed = false;
    }

    public void setMarked() {
        this.completed = true;
    }

}
