public class Event extends Task {
    private static final String type = "[E]";
    private String from;
    private String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return type + super.toString() + "(from: " + from + "to:" + to +  ")";
    }
}
