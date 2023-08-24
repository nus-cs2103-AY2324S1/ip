public class Task {
    private String taskName;
    private boolean doneOrNot;

    public Task(String taskName) {
        this.taskName = taskName;
        this.doneOrNot = false;
    }

    public void setDone() {
        this.doneOrNot = true;
    }

    public void setUndone() {
        this.doneOrNot = false;
    }

    public String getTaskStatus() {
        return this.doneOrNot ? "[X]" : "[ ]";
    }

    public String getTaskName() {
        return this.taskName;
    }
}
