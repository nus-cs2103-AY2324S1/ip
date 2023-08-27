package duke.task;

public class EventsTask extends Task {
    private final String TYPE = "E";
    private final String from;
    private final String to;

    public EventsTask(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                TYPE, super.toString(), this.from, this.to);
    }

    @Override
    public String toStringStore() {
        String mark = this.isDone ? "1": "0";
        return String.format("%s,%s,%s,%s,%s", TYPE, mark, this.name, this.from, this.to);
    }
}
