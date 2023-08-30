public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description / from / to of a event cannot be empty.");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + from + "-" + to + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
