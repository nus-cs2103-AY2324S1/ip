package joi.utils;

public class Event extends Task {
    private final String start;
    private final String end;
    public Event (String description) throws InvalidCommandException {
        super();

        if (description.length() <= 6) {
            throw new InvalidCommandException("Event has to have both start and end data.");
        }
        description = description.substring(6);

        String[] tokens = description.split("/");
        if (tokens.length != 3) {
            throw new InvalidCommandException("Event has to have both start and end data.");
        }
        this.description = tokens[0];
        this.start = tokens[1].substring(5);
        this.end = tokens[2].substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + "to: "+ this.end + ")";
    }
}
