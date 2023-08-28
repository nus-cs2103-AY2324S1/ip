public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(from:%sto:%s)", super.toString(), this.from, this.to);
    }

    @Override
    public String encodeTask() {
        return String.format("E;%s;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.from, this.to);
    }
}
