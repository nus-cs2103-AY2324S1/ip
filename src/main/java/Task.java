public abstract class Task {

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getTask();

    public abstract String getTaskType();

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String checkDone() {
        return isDone ? "X" : " ";
    }
}
