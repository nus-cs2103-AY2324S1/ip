public class Event extends Task{
    private final String start;
    private final String end;

    public Event(String descr, String start, String end) {
        super(descr);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String[] parts = this.start.split(" ");
        String from = parts[0];
        String restOfFrom = this.start.substring(from.length()).trim();

        String[] segments = this.end.split(" ");
        String to = segments[0];
        String restOfTo = this.end.substring(to.length()).trim();
        return "[E]" + super.toString() + "(" + from + ": " + restOfFrom + " " + to + ": " + restOfTo + ")";
    }
}
