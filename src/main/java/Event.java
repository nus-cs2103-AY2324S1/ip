public class Event extends Task {
    String timestart;
    String timeend;
    public Event(String name, String timestart, String timeend) {
        super(name);
        this.timestart = timestart;
        this.timeend = timeend;
    }

    public String toString() {
        return ("[E]" + super.toString() + " (from: " + timestart + " to: " + timeend + ")");
    }
}
