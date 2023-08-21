public class Event extends Task{

    protected String start;
    protected String end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String time = String.format(" (from: %s to: %s)", start, end);
        return "[E]" + super.toString() + time;
    }
}
