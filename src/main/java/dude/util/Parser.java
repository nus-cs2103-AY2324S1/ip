package dude.util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import dude.exception.ParserException;

/**
 * Represents a parser that parses user input and deals with making sense of the user command.
 */
public class Parser {
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
                throw new ParserException("invalid command format. try: \n"
                        + "delete /t <task index> OR \n"
                        + "delete /n <note index> OR \n");
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

    private static int parseIndex(String fullCommand) throws ParserException {
        String[] commandDetails = fullCommand.split("\\s+/n\\s+|\\s+/t\\s+|\\s+");

        if (commandDetails.length < 2) {
            throw new ParserException("something is missing... \n try again.");
        }

        try {
            return Integer.parseInt(commandDetails[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("index" + commandDetails[1] + "is invalid... ");
        }
    }

    private static String parseDescription(String fullCommand) throws ParserException {
        String[] commandDetails = fullCommand.split(" ", 2);

        if (commandDetails.length < 2) {
            throw new ParserException("something is missing... a description?");
        }

        return commandDetails[1].trim();
    }

    private static String[] parseDeadline(String fullCommand) throws ParserException {

        if (!fullCommand.matches("deadline(.+)/by(.+)")) {
            throw new ParserException("invalid command format. try: \n"
                    + "deadline <description> /by dd-mm-yyyy hh:mm");
        }

        String[] commandDetails = fullCommand.split("deadline\\s+|\\s+/by\\s+");

        if (commandDetails.length < 3) {
            throw new ParserException("something is missing. try: \n"
                    + "deadline <description> /by dd-mm-yyyy hh:mm");
        }

        return commandDetails;
    }

    private static String[] parseEvent(String fullCommand) throws ParserException {

        if (!fullCommand.matches("event(.+)/from(.+)/to(.+)")) {
            throw new ParserException("invalid command format. try: \n"
                    + "event <description> /from dd-mm-yyyy hh:mm /to dd-mm-yyyy hh:mm");
        }

        String[] commandDetails = fullCommand.split("event\\s+|\\s+/from\\s+|\\s+/to\\s+");

        if (commandDetails.length < 4) {
            throw new ParserException("something is missing. try: \n"
                    + "event <description> /from dd-mm-yyyy hh:mm /to dd-mm-yyyy hh:mm");
        }

        return commandDetails;
    }

    private static LocalDateTime parseDateTime(String dateTimeString) throws ParserException {
        try {
            return LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, DATE_FORMATTER);
                return date.atTime(LocalTime.MIDNIGHT);
            } catch (DateTimeParseException ex) {
                throw new ParserException("invalid date time format. try: \n "
                        + "dd-mm-yyyy hh:mm OR dd-mm-yyyy");
            }
        }
    }
}
