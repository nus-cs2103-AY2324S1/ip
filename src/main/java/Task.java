public abstract class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    void markDone() {
        this.isDone = true;
    }

    void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public String exportToText() {
        return String.format("%s,>%s", this.name, this.isDone ? "X" : "O");
    }
}
