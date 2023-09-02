public abstract class Task {
    protected String name;
    protected boolean isDone;

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String showStatus() {
        return (isDone ? "X" : " ");
    }

    public String showName() {
        return this.name;
    }
    public abstract String identifier();

    public abstract String toFile();

    public String showStatusAsFile() { return (isDone ? "1" : "0"); }
    public String toString() {
        return String.format("[%s] [%s] %s", this.identifier(), this.showStatus(), this.showName());
    }
}
