public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String fileFormat() {
        return "E " + super.fileFormat() + " | " + this.start + "-" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " " + "to: " + end + ")";
    }
}