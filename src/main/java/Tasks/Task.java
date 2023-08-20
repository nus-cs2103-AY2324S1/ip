package Tasks;

public class Task {
    private String taskDesc;
    private boolean isDone;

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

    public void markedAsDone() {
        this.isDone = true;
    }

    public void markedAsUndone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTaskDesc();
    }


}
