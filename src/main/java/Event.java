public class Event extends Task {
    String from;
    String to;
    public Task(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                (super.isDone ? "X" : " "), super.taskName, this.from, this.to);
    }
}
