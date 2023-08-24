public class Event extends Task {
    protected String time;
    protected String date;

    public Event(String description, String time, String date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.date + " to: " + this.time + ")";
    }
}

