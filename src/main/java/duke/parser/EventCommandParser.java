package duke.parser;

import java.util.regex.Matcher;

import duke.exceptions.MissingDescriptionException;
import duke.exceptions.IncorrectCommandFormatException;

public class EventCommandParser extends CommandParser {
    public EventCommandParser() {
        super("event", "^(?P<command>event)(?: (?P<description>.*?) /from (?P<from_time>\\d{4}-\\d{2}-\\d{2}) /to (?P<to_time>\\d{4}-\\d{2}-\\d{2}))?$");
    }

    @Override
    protected void validate(Matcher matcher) throws IncorrectCommandFormatException, MissingDescriptionException {
        String description = matcher.group("description");
        String fromTime = matcher.group("from_time");
        String toTime = matcher.group("to_time");

        if (description == null || description.trim().isEmpty()) {
            throw new MissingDescriptionException("Description is missing");
        }

        if (fromTime == null && toTime == null) {
            throw new IncorrectCommandFormatException("Both /from and /to are missing");
        } else if (fromTime == null) {
            throw new IncorrectCommandFormatException("Missing /from");
        } else if (toTime == null) {
            throw new IncorrectCommandFormatException("Missing /to");
        }
    }
}
