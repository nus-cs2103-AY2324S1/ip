package kevin.taskList;

public class Task {
    protected final String name;
    protected boolean isDone;

    public Task(Boolean isDone, String name) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void mark() {
        this.setIsDone(true);
    }

    public void unmark() {
        this.setIsDone(false);
    }

    public String isDone() {
        return this.isDone ? "X" : " ";
    }

    public String toText() {
        return "Task - "  + isDone + " - " + name + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + this.isDone() + "] " + this.name;
    }
}
