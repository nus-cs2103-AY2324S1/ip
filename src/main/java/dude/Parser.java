package dude;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dude.command.AddDeadlineCommand;
import dude.command.AddEventCommand;
import dude.command.AddNoteCommand;
import dude.command.AddToDoCommand;
import dude.command.Command;
import dude.command.DeleteNoteCommand;
import dude.command.DeleteTaskCommand;
import dude.command.ExitCommand;
import dude.command.FindCommand;
import dude.command.ListCommand;
import dude.command.MarkCommand;
import dude.command.UnknownCommand;
import dude.command.UnmarkCommand;
import dude.exception.ParseException;

/**
 * Represents a parser that parses user input and deals with making sense of the user command.
 */
public class Parser {
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param fullCommand The full user command input.
     * @return A command object representing the user's intent.
     */
    public static Command parse(String fullCommand) {
        String commandType = getCommandType(fullCommand);
        Command c;

        if (commandType.equals("bye")) {

            c = new ExitCommand();

        } else if (commandType.equals("list")) {

            c = new ListCommand();

        } else if (commandType.equals("mark")) {

            int taskIndex = parseIndex(fullCommand);
            c = new MarkCommand(taskIndex);

        } else if (commandType.equals("unmark")) {

            int taskIndex = parseIndex(fullCommand);
            c = new UnmarkCommand(taskIndex);

        } else if (commandType.equals("delete")) {

            int index = parseIndex(fullCommand);

            if (fullCommand.matches("(.+)/t(.+)")) {
                c = new DeleteTaskCommand(index);
            } else if (fullCommand.matches("(.+)/n(.+)")) {
                c = new DeleteNoteCommand(index);
            } else {
                throw new ParseException("Invalid command format: " + fullCommand);
            }

        } else if (commandType.equals("todo")) {

            String taskDescription = parseDescription(fullCommand);
            c = new AddToDoCommand(taskDescription);

        } else if (commandType.equals("deadline")) {

            String[] commandDetails = parseDeadline(fullCommand);
            LocalDateTime byDateTime = parseDateTime(commandDetails[2]);
            c = new AddDeadlineCommand(commandDetails[1], byDateTime);

        } else if (commandType.equals("event")) {

            String[] commandDetails = parseEvent(fullCommand);

            LocalDateTime fromDateTime = parseDateTime(commandDetails[2]);
            LocalDateTime toDateTime = parseDateTime(commandDetails[3]);
            c = new AddEventCommand(commandDetails[1], fromDateTime, toDateTime);

        } else if (commandType.equals("note")) {

            String noteDescription = parseDescription(fullCommand);
            c = new AddNoteCommand(noteDescription);

        } else if (commandType.equals("find")) {

            String searchKeywords = parseDescription(fullCommand);
            c = new FindCommand(searchKeywords);

        } else {

            c = new UnknownCommand(fullCommand);

        }
        return c;
    }

    private static String getCommandType(String fullCommand) {
        String[] commandDetails = fullCommand.split(" ", 2);
        String commandType = commandDetails[0];
        return commandType;
    }

    private static int parseIndex(String fullCommand) throws ParseException {
        String[] commandDetails = fullCommand.split("\\s+/n\\s+|\\s+/t\\s+|\\s+");

        if (commandDetails.length < 2) {
            throw new ParseException("Invalid command format: " + commandDetails[0]);
        }

        try {
            return Integer.parseInt(commandDetails[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid task index: " + commandDetails[1]);
        }
    }

    private static String parseDescription(String fullCommand) throws ParseException {
        String[] commandDetails = fullCommand.split(" ", 2);

        if (commandDetails.length < 2) {
            throw new ParseException("Description cannot be empty.");
        }

        return commandDetails[1].trim();
    }

    private static String[] parseDeadline(String fullCommand) throws ParseException {

        if (!fullCommand.matches("deadline(.+)/by(.+)")) {
            throw new ParseException("Invalid command format: " + fullCommand);
        }

        String[] commandDetails = fullCommand.split("deadline\\s+|\\s+/by\\s+");

        if (commandDetails.length < 3) {
            throw new ParseException("Invalid command format: " + fullCommand);
        }

        return commandDetails;
    }

    private static String[] parseEvent(String fullCommand) throws ParseException {

        if (!fullCommand.matches("event(.+)/from(.+)/to(.+)")) {
            throw new ParseException("Invalid command format: " + fullCommand);
        }

        String[] commandDetails = fullCommand.split("event\\s+|\\s+/from\\s+|\\s+/to\\s+");

        if (commandDetails.length < 4) {
            throw new ParseException("Invalid command format: " + fullCommand);
        }

        return commandDetails;
    }

    private static LocalDateTime parseDateTime(String dateTimeString) throws ParseException {
        try {
            return LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date/time format: " + dateTimeString);
        }
    }
}
