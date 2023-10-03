package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.commands.taskcommands.EventCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;

/**
 * Represents a parser that parses user input into a EventCommand object.
 */
public class EventCommandParser extends CommandParser {

    /**
     * Creates a EventCommandParser object.
     */
    // CHECKSTYLE.OFF: LineLength
    public EventCommandParser() {
        super("event",
                "^(?<command>event)(?: (?<description>.*?)?(?<from> /from.*?)?(?<fromTime>\\d{4}-\\d{1,2}-\\d{1,2})?(?<to> /to.*?)?(?<toTime>\\d{4}-\\d{1,2}-\\d{1,2})?)?$");
    }
    //CHECKSTYLE.ON: LineLength

    @Override
    protected void validate(Matcher matcher)
            throws IncorrectCommandFormatException, MissingDescriptionException, InvalidTimeFormatException {
        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        String fromTime = matcher.group("fromTime");
        String toTime = matcher.group("toTime");

        if (description == null || description.trim().isEmpty()) {
            throw new MissingDescriptionException("Event");
        }

        if (from == null && to == null) {
            throw new IncorrectCommandFormatException("Both /from and /to are missing");
        } else if (from == null) {
            throw new IncorrectCommandFormatException("Missing /from");
        } else if (to == null) {
            throw new IncorrectCommandFormatException("Missing /to");
        }

        if (fromTime == null || fromTime.trim().isEmpty()) {
            throw new IncorrectCommandFormatException("Missing from time");
        }

        if (toTime == null || toTime.trim().isEmpty()) {
            throw new IncorrectCommandFormatException("Missing to time");
        }

        try {
            java.time.LocalDate.parse(fromTime);
        } catch (java.time.DateTimeException e) {
            throw new InvalidTimeFormatException("Time formatting for from time incorrect");
        }

        try {
            java.time.LocalDate.parse(toTime);
        } catch (java.time.DateTimeException e) {
            throw new InvalidTimeFormatException("Time formatting for to time incorrect");
        }
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        return new EventCommand(matcher);
    }
}
