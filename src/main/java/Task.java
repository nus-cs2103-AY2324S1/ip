public class Task {
    private boolean completed = false;
    private String taskName = "";

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String checkbox = "[" + (completed ? "X" : " ") + "]";
        return checkbox + " " + taskName;
    }
}