package tasks;

public class Task {

    private final String desc;
    private boolean isCompleted;

    public Task(String desc) {
        this.desc = desc;
        this.isCompleted = false;
    }

    public void taskCompleted() {
        this.isCompleted = true;
    }

    public void taskNotCompleted() {
        this.isCompleted = false;
    }

    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "]" + " " + this.desc;
    }
}
