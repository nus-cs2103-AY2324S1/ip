package joi.utils;

import joi.parser.DateTimeParse;

import java.util.Date;

public class Deadline extends Task {
    private final Date end;

    public Deadline (String description) throws InvalidCommandException {
        super();

        if (description.length() <= 9) {
            throw new InvalidCommandException("Deadline has to have an end date.");
        }
        description = description.substring(9);

        String[] tokens = description.split(" /by ");
        if (tokens.length != 2) {
            throw new InvalidCommandException("Deadline has to have an end date.");
        }
        this.description = tokens[0];
        this.end = DateTimeParse.parseDateTime(tokens[1]);
    }

    public Deadline(String description, String end, boolean status) {
        super();

        this.description = description;
        this.end = DateTimeParse.parseDateTime(end);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end + ")";
    }

    public String getEventType() {
        return "deadline";
    }

    @Override
    public String getDescription() {
        return this.description + "@" + this.end;
    }
}
