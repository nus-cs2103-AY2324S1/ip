package emiya.task;

abstract public class Task {
    protected boolean isCompleted;
    protected String nameOfTask;

    public Task(boolean isCompleted, String nameOfTask) {
        this.isCompleted = isCompleted;
        this.nameOfTask = nameOfTask;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + nameOfTask;
        } else {
            return "[ ] " + nameOfTask;
        }
    }

    abstract public String typeOfString();
    public String statusString() {
        if (isCompleted) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

    abstract public String taskDetailsString();

    public void setUnmarked() {
        this.isCompleted = false;
    }

    public void setMarked() {
        this.isCompleted = true;
    }

}
