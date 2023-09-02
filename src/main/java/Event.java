public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("%s | %d | %s | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description,
                this.start,
                this.end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
