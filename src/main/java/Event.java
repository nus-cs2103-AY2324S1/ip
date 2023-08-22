public class Event extends Task {
    protected String e_start;
    protected String e_end;

    public Event(String description, String e_start, String e_end) {
        super(description, TaskType.EVENT);
        this.e_start = e_start;
        this.e_end = e_end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + e_start + " to: " + e_end + ")";
    }
}