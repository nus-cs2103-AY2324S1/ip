public class Task {
    protected String desc;
    protected boolean isCompleted;

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
