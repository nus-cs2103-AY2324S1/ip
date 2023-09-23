package data.tasks;

import common.DateParser;

import data.exception.InvalidParamException;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String detail,
                 LocalDateTime from,
                 LocalDateTime to) throws InvalidParamException {
        super(detail, from);
        if (from.isAfter(to)) {
            throw new InvalidParamException(new String[]{
                "Starting date "
                + DateParser.toDisplayString(from)
                + " cannot be after the end date "
                + DateParser.toDisplayString(to)
            });
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)",
            super.toString(),
            DateParser.toDisplayString(from),
            DateParser.toDisplayString(to)
        );
    }

    @Override
    public String toFileFormatString() {
        return String.format(
            "E|%s|%s|%s",
            super.toFileFormatString(),
            DateParser.toFileString(from),
            DateParser.toFileString(to)
        );
    }
}
