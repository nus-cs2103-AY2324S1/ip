public class Event extends SingleTask {
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "OK DONE ALR added your Event ah:\n" +
                "[E][" + super.getStatusIcon() + "] " + description +"(from: "+ this.from + " to: " + this.to + ")";
    }
    @Override
    public String listString() {
        return ". [E][" + super.getStatusIcon() + "] " + description +"(from: "+ this.from + " to: " + this.to + ")";
    }
}

