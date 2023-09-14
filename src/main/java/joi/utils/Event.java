package joi.utils;

import joi.parser.DateTimeParse;

import java.util.Date;

public class Event extends Task {
    private final Date start;
    private final Date end;
    public Event(String description) throws InvalidCommandException {
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
        this.start = DateTimeParse.parseDateTime(tokens[1].substring(5));
        this.end = DateTimeParse.parseDateTime(tokens[2].substring(3));
    }

    public Event(String description, String start, String end, boolean status) {
        super();

        this.description = description;
        this.start = DateTimeParse.parseDateTime(start);
        this.end = DateTimeParse.parseDateTime(end);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + "to: "+ this.end + ")";
    }

    @Override
    public String getEventType() {
        return "event";
    }

    @Override
    public String getDescription() {
        return this.description + "@" + this.start + "@" + this.end;
    }
}
