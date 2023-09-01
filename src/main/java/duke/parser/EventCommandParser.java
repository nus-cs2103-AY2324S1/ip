package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.commands.TaskCommands.EventCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;

public class EventCommandParser extends CommandParser {
    public EventCommandParser() {
        super("event",
                "^(?<command>event)(?: (?<description>.*?)?(?<from> /from.*?)?(?<fromTime>\\d{4}-\\d{2}-\\d{2})?(?<to> /to.*?)?(?<toTime>\\d{4}-\\d{2}-\\d{2})?)?$");
    }

    @Override
    protected void validate(Matcher matcher) throws IncorrectCommandFormatException, MissingDescriptionException {
        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        String fromTime = matcher.group("fromTime");
        String toTime = matcher.group("toTime");
        System.out.println(description + "." + from + "." + to + "." + fromTime + "." + toTime);

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
            throw new IncorrectCommandFormatException("Time formatting for from time incorrect");
        }

        try {
            java.time.LocalDate.parse(toTime);
        } catch (java.time.DateTimeException e) {
            throw new IncorrectCommandFormatException("Time formatting for to time incorrect");
        }
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        return new EventCommand(matcher);
    }
}