abstract public class Task {
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
    public String getCheckBox() {
        return this.isDone ? "[X]": "[ ]";
    }
    @Override
    public String toString() {
        return String.format("%s %s", this.getCheckBox(), this.name);
    }
}
