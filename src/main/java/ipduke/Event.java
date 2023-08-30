package ipduke;

public class Event extends Task {
    private String start;
    private String end;
    Event(String task, String start, String end, boolean done) {
        super(task, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start, this.end);
    }

    @Override
    public String getTaskFileString() {
        return "E" + " , " + super.getTaskFileString() + " , " + this.start + " , " + this.end;
    }
}
