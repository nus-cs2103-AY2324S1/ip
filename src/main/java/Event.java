public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) throws DukeException {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
