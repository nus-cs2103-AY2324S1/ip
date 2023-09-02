package duke.commands.TaskCommands;

import java.time.LocalDate;
import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.commands.TaskCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Event;

/**
 * Represents a command that adds a event task to the task list.
 */
public class EventCommand extends TaskCommand {

    /**
     * The start date of the event.
     */
    private LocalDate from;

    /**
     * The end date of the event.
     */
    private LocalDate to;

    /**
     * Creates a EventCommand object.
     * 
     * @param matcher The matcher object used to extract the description and event.
     * @throws MissingDescriptionException If the description is missing.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     * @throws InvalidTimeFormatException If the time provided is invalid.
     */
    public EventCommand(Matcher matcher)
            throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        super(matcher);
        this.from = extractFrom();
        this.to = extractTo();
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws MissingDescriptionException, IncorrectCommandFormatException {
        taskListStorage.addEvent(new Event(this.description, this.from, this.to));
    }

    private LocalDate extractFrom() throws IncorrectCommandFormatException, InvalidTimeFormatException {
        if (matcher.matches()) {
            String fromTime = matcher.group("fromTime");
            if (fromTime == null || fromTime.trim().isEmpty()) {
                throw new IncorrectCommandFormatException("event <description> /from <from_date> /to <to_date>");
            }
            try {
                return LocalDate.parse(fromTime.trim());
            } catch (java.time.format.DateTimeParseException e) {
                throw new InvalidTimeFormatException("deadline <description> /by <date>");
            }        }
        return null;
    }

    private LocalDate extractTo() throws IncorrectCommandFormatException, InvalidTimeFormatException {
        if (matcher.matches()) {
            String toTime = matcher.group("toTime");            
            if (toTime == null || toTime.trim().isEmpty()) {
                throw new IncorrectCommandFormatException("event <description> /from <from_date> /to <to_date>");
            }
            try {
                return LocalDate.parse(toTime.trim());
            } catch (java.time.format.DateTimeParseException e) {
                throw new InvalidTimeFormatException("deadline <description> /by <date>");
            }        }
        return null;
    }
}