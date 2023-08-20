public abstract class Task {
    protected boolean isComplete;
    protected String name;
    public Task(String name) {
        this.isComplete = false;
        this.name = name;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void markDone() {
        this.isComplete = true;
    }

    public void markUndone() {
        this.isComplete = false;
    }
    public String getName() {
        return this.name;
    }

    public String getMarking() {
        if (isComplete()) {
            return "[X]";
        }
        return "[ ]";
    }
}
