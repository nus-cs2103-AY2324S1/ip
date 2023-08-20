public class Task {
    private boolean isDone;
    private final String name;
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }
    public void markTask() {
        this.isDone = true;
    }
    public void unmarkTask() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        String checkBox = this.isDone ? "[X]": "[ ]";
        return String.format("%s %s", checkBox, this.name);
    }
}
