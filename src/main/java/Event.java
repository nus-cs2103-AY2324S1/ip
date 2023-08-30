import java.util.StringJoiner;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: "+ this.end +  ")";
    }

    @Override
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("E").add(super.toFileFormat()).add(this.start).add(this.end);
        return joiner.toString();
    }
}
