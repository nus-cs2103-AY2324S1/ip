package phi;

public abstract class Task {
    protected enum Type {T, D, E}

    protected Type taskType;
    protected boolean done;
    protected String taskName;

    public Task(Type taskType, boolean done, String taskName) {
        this.taskType = taskType;
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
                ? String.format("[%s][X] %s", taskType, taskName)
                : String.format("[%s][ ] %s", taskType, taskName);
    }

    public abstract String outputFormat();
}
