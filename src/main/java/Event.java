public class Event extends Task {

    private final String start;
    private final String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTask() {
        return String.format("[%s][E] %s (from: %s to: %s)", super.checkDone(), super.getName(), start, end);
    }
}
