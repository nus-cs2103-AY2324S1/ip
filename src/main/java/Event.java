public class Event extends Task{
    private String type = "E";
    private String from;
    private String to;

    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
