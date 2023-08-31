public class Task {
    boolean isDone;
    final String taskName;

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String toMemoryFormat() {
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.isDone ? "X" : " "), this.taskName);
    }
}
