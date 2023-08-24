public abstract class Task {
    String taskName;
    boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }
    public String toString(){
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    };

    // mark task as done and print out the line
    public abstract void markDone();

    // mark task as undone and print out the line
    public abstract void unmarkDone();
}
