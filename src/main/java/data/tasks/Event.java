package data.tasks;

import java.time.LocalDateTime;

import common.DateParser;
import data.exception.InvalidParamException;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String detail,
                 LocalDateTime from,
                 LocalDateTime to) throws InvalidParamException {
        super(detail, from);
        if (from.isAfter(to)) {
            throw new InvalidParamException(new String[]{
                "Starting date cannot be after the end date!"
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
