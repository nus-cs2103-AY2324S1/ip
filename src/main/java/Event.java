public class Event extends Task {

    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        String[] interval = period.split(" /to ");
        return "[E]" + super.toString() + " (from: " + interval[0] + " to: " + interval[1] + ")";
    }
}
