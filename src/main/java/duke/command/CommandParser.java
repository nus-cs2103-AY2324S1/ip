package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CommandParser {
    public static Command parse(String fullCommand) throws ParsingException {

        String[] splits = fullCommand.strip().split(" ", 2);
        String command = splits[0].toLowerCase();
        String detail = splits.length == 2 ? splits[1].strip() : "";

        switch (command) {

            case "bye":

                return new ExitCommand();

            case "todo":

                if (detail.isBlank())
                    throw new ParsingException(ParsingException.ExceptionType.MISSING_BODY);
                return new AddCommand(new Todo(detail));

            case "deadline":

                if (detail.isBlank())
                    throw new ParsingException(ParsingException.ExceptionType.MISSING_BODY);
                try {
                    String[] deadlineSplits = detail.split("/by", 2);
                    String deadlineDescription = deadlineSplits[0].strip();
                    String deadlineStr = deadlineSplits[1].strip();
                    if (deadlineStr.isBlank() || deadlineDescription.isBlank())
                        throw new ParsingException(ParsingException.ExceptionType.MISSING_BODY);

                    try {
                        LocalDateTime deadlineDate = LocalDateTime.parse(deadlineStr);
                        return new AddCommand(new Deadline(deadlineDescription, deadlineDate));
                    } catch (DateTimeParseException e) {
                        return new AddCommand(new Deadline(deadlineDescription, deadlineStr));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ParsingException(ParsingException.ExceptionType.IMPROPER_FORMAT);
                }

            case "event":

                if (detail.isBlank())
                    throw new ParsingException(ParsingException.ExceptionType.MISSING_BODY);
                try {
                    String[] eventSplit1 = detail.split("/from", 2);
                    String[] eventSplit2 = eventSplit1[1].split("/to", 2);
                    String eventDescription = eventSplit1[0].strip();
                    String startDate = eventSplit2[0].strip();
                    String endDate = eventSplit2[1].strip();
                    if (eventDescription.isBlank() || startDate.isBlank() || endDate.isBlank())
                        throw new ParsingException(ParsingException.ExceptionType.MISSING_DETAIL);
                    System.out.println("Roger that. Preparations will be underway.");
                    try {
                        LocalDateTime eventStartDate = LocalDateTime.parse(startDate);
                        LocalDateTime eventEndDate = LocalDateTime.parse(endDate);
                        return new AddCommand(new Event(eventDescription, eventStartDate, eventEndDate));
                    } catch (DateTimeParseException e) {
                        return new AddCommand(new Event(eventDescription, startDate, endDate));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ParsingException(ParsingException.ExceptionType.IMPROPER_FORMAT);
                }

            case "mark":

                if (detail.isBlank()) {
                    throw new ParsingException(ParsingException.ExceptionType.MISSING_INDEX);
                }

                if (detail.equalsIgnoreCase("all")) {
                    return new MarkCommand(null);
                }

                try {
                    int index = Integer.parseInt(detail) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
                }

            case "unmark":

                if (detail.isBlank())
                    throw new ParsingException(ParsingException.ExceptionType.MISSING_INDEX);

                if (detail.equalsIgnoreCase("all")) {
                    return new UnmarkCommand(null);
                }

                try {
                    int index = Integer.parseInt(detail) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
                }

            case "list":

                if (detail.isBlank()) {
                    return new ListCommand();
                } else {
                    throw new ParsingException(ParsingException.ExceptionType.EXCESS_INPUT);
                }

            case "delete":

                if (detail.isBlank()) {
                    throw new ParsingException(ParsingException.ExceptionType.MISSING_INDEX);
                }

                if (detail.equalsIgnoreCase("all")) {
                    return new DeleteCommand(null);
                }

                try {
                    int index = Integer.parseInt(detail) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
                }

            case "thanks":

                return new MessageCommand("...No problem.");

            case "zzz":

                return new DocRestCommand();

            case "hi":

                return new MessageCommand("Greetings.");

            default:

                return new MessageCommand("Sorry, I didn't quite catch that. Are you having enough sleep?");

        }
    }

}
