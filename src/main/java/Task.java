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

    public String getTask() {
        return String.format("[%s][T] %s", checkDone(), name);
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    protected String checkDone() {
        return isDone ? "X" : " ";
    }
}
