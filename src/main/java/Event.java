public class Event extends Task {
    String from;
    String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toMemoryFormat() {
        return String.format("T | %s | %s | %s | %s", (super.isDone ? "1" : "0"),
                super.taskName, this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                (super.isDone ? "X" : " "), super.taskName, this.from, this.to);
    }
}
