package dude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dude.command.*;

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
        String[] commandDetails = fullCommand.split(" ", 2);
        String commandType = commandDetails[0];
        Command c;

        if (commandType.equals("bye")) {
            c = new ExitCommand();
        } else if (commandType.equals("list")) {
            c = new ListCommand();
        } else if (commandType.equals("mark")) {
            int taskIndex = Integer.parseInt(commandDetails[1]) - 1;
            c = new MarkCommand(taskIndex);
        } else if (commandType.equals("unmark")) {
            int taskIndex = Integer.parseInt(commandDetails[1]) - 1;
            c = new UnmarkCommand(taskIndex);
        } else if (commandType.equals("delete")) {
            String[] details = commandDetails[1].split(" ");
            String itemType = details[0];
            int index = Integer.parseInt(details[1]) - 1;
            if (itemType.equals("/t")) {
                c = new DeleteTaskCommand(index);
            } else if (itemType.equals("/n")) {
                c = new DeleteNoteCommand(index);
            } else {
                c = new UnknownCommand(fullCommand);
            }
        } else if (commandType.equals("todo")) {
            String taskDescription = commandDetails[1].trim();
            c = new AddToDoCommand(taskDescription);
            // if (userInputDetails.length > 1)
            // System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } else if (commandType.equals("deadline")) {
            String[] taskDetails = commandDetails[1].split("\\s/by\\s");
            String taskDescription = taskDetails[0].trim();
            LocalDateTime byDateTime = LocalDateTime.parse(taskDetails[1].trim(), DATETIME_FORMATTER);
            c = new AddDeadlineCommand(taskDescription, byDateTime);
            // if (userInputDetails.length == 1) {
            // System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        } else if (commandType.equals("event")) {
            String[] taskDetails = commandDetails[1].split("\\s/from\\s|\\s/to\\s");
            String taskDescription = taskDetails[0].trim();
            LocalDateTime fromDateTime = LocalDateTime.parse(taskDetails[1].trim(), DATETIME_FORMATTER);
            LocalDateTime toDateTime = LocalDateTime.parse(taskDetails[2].trim(), DATETIME_FORMATTER);
            c = new AddEventCommand(taskDescription, fromDateTime, toDateTime);
            // if (userInputDetails.length == 1) {
            // System.out.println("OOPS!!! The description of an event cannot be empty.");
        } else if (commandType.equals("note")) {
            String noteDescription = commandDetails[1].trim();
            c = new AddNoteCommand(noteDescription);
        } else if (commandType.equals("find")) {
            String searchKeywords = commandDetails[1].trim();
            c = new FindCommand(searchKeywords);
        } else {
            c = new UnknownCommand(fullCommand);
        }
        return c;
    }
}
