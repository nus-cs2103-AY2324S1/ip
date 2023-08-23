public abstract class Task {
    String type;
    boolean done;
    String taskName;

    public Task(String type, boolean done, String taskName) {
        this.type = type;
        this.done = done;
        this.taskName = taskName;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return done
                ? String.format("[%s][X] %s", type, taskName)
                : String.format("[%s][ ] %s", type, taskName);
    }
}
