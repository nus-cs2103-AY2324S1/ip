public class Event extends Task{

    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.split(" ", 2)[1];
        this.to = to.split(" ", 2)[1];
    }

    @Override
    public String toString() {
        String ret = "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")" ;
        return ret;
    }
}
