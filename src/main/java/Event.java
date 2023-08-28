public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return super.toString() + "(from:" + this.from + "to:" + this.to + ")";
    }
}
