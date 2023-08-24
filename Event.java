public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, int id, String from, String to) {
        super(description, id);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return String.valueOf(this.id) + ". " + "[E]" + status + this.description +
                " (from: " + from + " to: " + to + ")";
    }


}
