public class Event extends Task {

    protected String from;
    protected String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event EventCon(String description, String from, String to) throws InvalidTaskCreationException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a Event Task cannot be empty.");
        } else if (from.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The from time of a Event Task cannot be empty.");
        } else if (to.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The to time of a Event Task cannot be empty.");
        } else {
            return new Event(description, from, to);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
