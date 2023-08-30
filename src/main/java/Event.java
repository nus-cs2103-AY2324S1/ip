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
    public String stringifyTask() {
        return String.format("E|%d|%s|from %s to %s", this.done ? 1 : 0, this.name, this.from, this.to);
    }

    @Override
    public String toString() {
        return type + super.toString() + "(from: " + from + "to:" + to +  ")";
    }
}
