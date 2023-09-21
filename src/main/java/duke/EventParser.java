package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;

/**
 * The EventParser class is responsible for parsing user input and creating an AddCommand
 * for event-related tasks in the Duke application.
 */
public class EventParser {

    /**
     * Parses the user input and creates an AddCommand for an event task.
     *
     * @param input The user input containing event task details.
     * @return An AddCommand object representing the event task.
     * @throws DukeException.EventFormatException If the input format for the event task is incorrect.
     * @throws DukeException.EventException       If there is an issue creating the event task.
     */
    public static AddCommand parseEventCommand(String input) throws DukeException {

        if (input.isEmpty()) {
            throw new DukeException.EventException();
        }

        String[] splitTheArgumentsE = input.split("/from", 2);

        if (!input.contains("/from") || !splitTheArgumentsE[1].contains("/to")) {
            throw new DukeException.EventFormatException();
        }

        String theDescriptionE = splitTheArgumentsE[0].trim();
        String[] theDateTimeE = splitTheArgumentsE[1].trim().split("/to", 2);

        String fromDateTime = theDateTimeE[0].trim();
        String toDateTime = theDateTimeE[1].trim();

        DateTimeValidator validatorE = new DateTimeValidator("yyyy/MM/dd HHmm");
        boolean isDateValidE = validatorE.validateDate(fromDateTime) && validatorE.validateDate(toDateTime);

        if (isDateValidE) {
            LocalDateTime parsedFromDate = LocalDateTime.parse(fromDateTime,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            LocalDateTime parsedToDate = LocalDateTime.parse(toDateTime,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            return new AddCommand(AddCommand.TaskType.EVENT, theDescriptionE, parsedFromDate, parsedToDate);
        } else {
            throw new DukeException.EventException();
        }
    }
}
