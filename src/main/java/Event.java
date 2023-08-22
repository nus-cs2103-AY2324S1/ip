public class Event extends Task {

    private String from;
    private String to;

    public Event(String task, String[] period) {
        super(task);
        this.from = period[0];
        this.to = period[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + "to: " + this.to + ")";
    }

}
