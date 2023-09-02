package emiya.task;

public abstract class Task {
    protected boolean isCompleted;
    protected String taskDescription;

    public Task(boolean isCompleted, String taskDescription) {
        this.isCompleted = isCompleted;
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + taskDescription;
        } else {
            return "[ ] " + taskDescription;
        }
    }

    public abstract String typeOfString();
    public String printStatusString() {
        if (isCompleted) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

    public abstract String printTaskDetailsString();

    public void setUnmarked() {
        this.isCompleted = false;
    }

    public void setMarked() {
        this.isCompleted = true;
    }

}
