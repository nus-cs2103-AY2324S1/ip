package Tasks;

public class Task {
    protected String taskDesc;
    protected boolean isDone;

    public Task (String taskDesc, boolean isDone) {
        this.taskDesc = taskDesc;
        this.isDone = isDone;
    }

    public String getTaskDesc() {
        return this.taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getStatus() {
        return this.isDone ? "x" : " ";
    }

    public Task markedAsDone() {
        this.isDone = true;
        return this;
    }

    public Task markedAsUndone() {

        this.isDone = false;
        return this;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTaskDesc();
    }

    public String getData() {
        String marked = isDone ? "1" : "0";
        return " | " + marked + " | " + this.taskDesc;
    }
}
