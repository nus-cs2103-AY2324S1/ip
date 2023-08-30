public abstract class Task {
    enum Type {T, D, E}

    Type taskType;
    boolean done;
    String taskName;

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
