package Tasks;

public abstract class Task {
    private String name;
    private boolean isDone;
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }
    public String getName() {
        return this.name;
    }
    public boolean isDone() {
        return this.isDone;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return this.isDone() ? "[X]" : "[]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getName());
    }
    public String toString(boolean isWritten) {
        return String.format("TK %s", this.getName());
    }
}