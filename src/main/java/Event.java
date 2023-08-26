public class Event extends Task {
    protected String e_start;
    protected String e_end;

    public Event(String description, String e_start, String e_end) {
        super(description, TaskType.EVENT);
        this.e_start = e_start;
        this.e_end = e_end;
    }

    public Event(String description, String time, Boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        String[] splits = time.split("-");
        this.e_start = splits[0].trim();
        this.e_end = splits[1].trim();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + e_start + " to: " + e_end + ")";
    }
}