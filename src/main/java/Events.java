public class Events extends Task{
    String from, to;
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]"
                + super.toString() + " (from: " + from  + " to: " + to + ")";
    }
}
