public class Events extends Task{
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from.substring(5);
        this.to = to.substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
