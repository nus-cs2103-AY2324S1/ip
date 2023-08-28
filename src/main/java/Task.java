import java.io.Serializable;

class Task implements Serializable {
    protected boolean isDone = false;
    protected String name = "";

    public Task(String name) {
        this.name = name;
    }
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getTaskType() {
        return "";
    }

    public String getTimeInfo() {
        return "";
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unMarkTask() {
        this.isDone = false;
    }
}