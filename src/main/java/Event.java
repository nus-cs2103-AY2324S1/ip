public class Event extends Task {
    private String start_time;
    private String end_time;

    public Event(String description, String start_time, String end_time) {
        super(description);
        this.start_time = start_time;
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                " (from: " + start_time + " to: " + end_time + ")";
    }
}