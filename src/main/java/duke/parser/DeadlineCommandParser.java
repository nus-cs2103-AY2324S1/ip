package duke.parser;

import java.util.regex.Matcher;

import duke.exceptions.MissingDescriptionException;
import duke.exceptions.IncorrectCommandFormatException;

public class DeadlineCommandParser extends CommandParser {
    public DeadlineCommandParser() {
        super("deadline", "^(?P<command>deadline) (?P<description>.*?) /by (?P<by_time>.+)$");
    }

    @Override
    protected void validate(Matcher matcher) throws MissingDescriptionException, IncorrectCommandFormatException {
        String description = matcher.group("description");
        String byTime = matcher.group("by_time");

        if (description == null || description.trim().isEmpty()) {
            throw new MissingDescriptionException("Description is missing");
        }

        if (byTime == null || byTime.trim().isEmpty()) {
            throw new IncorrectCommandFormatException("Missing /by");
        }
    }
}
