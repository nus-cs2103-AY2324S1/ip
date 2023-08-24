public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) throws DukeException {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (start.trim().isEmpty() || end.trim().isEmpty()) {
            throw new DukeException("The start or end time of an event cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}