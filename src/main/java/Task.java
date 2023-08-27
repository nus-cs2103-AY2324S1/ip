public class Task {
    private String task;
    private boolean isDone;
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getDoneIcon() {
        return isDone ? "Y" : "N";
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String toSaveFormat() {
        return String.format("%s|%s", getDoneIcon(), task);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", getDoneIcon(), task);
    }
}
