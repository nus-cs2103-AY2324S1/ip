package duke.parser;

import java.util.regex.Matcher;

import duke.commands.Command;
import duke.commands.taskcommands.DeadlineCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;

/**
 * Represents a parser that parses user input into a DeadlineCommand object.
 */
public class DeadlineCommandParser extends CommandParser {

    /**
     * Creates a DeadlineCommandParser object.
     */
    // CHECKSTYLE.OFF: LineLength
    public DeadlineCommandParser() {
        super("deadline",
                "^(?<command>deadline)(?: ((?!/by)(?<description>.*?))?(?<by> /by.*?)?(?<byTime>\\d{4}-\\d{1,2}-\\d{1,2})?)?$");
    }
    //CHECKSTYLE.ON: LineLength


    @Override
    protected void validate(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        String description = matcher.group("description");
        String by = matcher.group("by");
        String byTime = matcher.group("byTime");

        if (description == null || description.trim().isEmpty()) {
            throw new MissingDescriptionException("Deadline");
        }

        if (by == null || by.trim().isEmpty()) {
            throw new IncorrectCommandFormatException("Missing /by");
        }

        if (byTime == null || byTime.trim().isEmpty()) {
            throw new IncorrectCommandFormatException("Missing deadline time");
        }

        try {
            java.time.LocalDate.parse(byTime);
        } catch (java.time.DateTimeException e) {
            throw new InvalidTimeFormatException("Time formatting for deadline incorrect");
        }
    }

    @Override
    protected Command createCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        return new DeadlineCommand(matcher);
    }
}
