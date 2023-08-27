package duke.task;

abstract public class Task {
    protected boolean isDone;
    protected final String name;
    public Task(String name, boolean isDone) {
        this.isDone = isDone;
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
    public abstract String toStringStore();
    public boolean containsKeyword(String keyword) {
        return this.name.contains(keyword);
    }
}
