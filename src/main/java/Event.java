public class Event extends Task {

    protected String from;
    protected String to;


    Event(String message, String from, String to) {
        super(message);
        this.from = from;
        this.to = to;
    }

    public String toSaveFormatString() {
        return "E | " + this.getStatusNumber() + " | " + this.message + " | " + from + " | " + to;
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.message
                + " (from: " + from + " to: " + to + ")";
    }
}
