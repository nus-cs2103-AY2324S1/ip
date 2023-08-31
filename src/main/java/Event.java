public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String taskString() {
        return "[E]" + super.taskString() + "(from: " + from + "to: " + to + ")";
    }
    /*@Override
    public String fileString() {
        return "[E]" + super.taskString() + "(from: " + from + "to: " + to + ")";
    }*/
}