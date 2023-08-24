public class Event extends Task {
    private String from;
    private String to;
    public Event(String d, String from, String to) {
        super(d);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        if (this.completed) marker = "[X]";
        return  "[E]" + marker + " " +
                this.description +
                "(from:" + this.from +
                "to:" + this.to + ")\n";
    }
}
